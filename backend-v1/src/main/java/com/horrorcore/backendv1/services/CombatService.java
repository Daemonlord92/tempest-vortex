package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CombatInformation;
import com.horrorcore.backendv1.dtos.enums.CombatStatus;
import com.horrorcore.backendv1.entities.*;
import com.horrorcore.backendv1.exceptions.PlayerCharacterNotFoundException;
import com.horrorcore.backendv1.repositories.MonsterRepository;
import com.horrorcore.backendv1.repositories.PlayerCharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CombatService {
    private final PlayerCharacterRepository playerCharacterRepository;
    private final MonsterRepository monsterRepository;

    private static final int DEFEND_REDUCTION_PERCENTAGE = 50;
    private static final int FLEE_SUCCESS_BASE_CHANCE = 50;
    private static final double CRITICAL_HIT_CHANCE = 0.15;
    private static final double CRITICAL_HIT_MULTIPLIER = 1.5;

    @Transactional
    public CombatInformation processCombatTurn(CombatInformation combatInfo) {
        // Validate combat is still active
        if (isCombatOver(combatInfo)) {
            log.info("Combat has ended");
            return combatInfo;
        }

        // Process all entities until it's the player's turn again
        boolean playerTurnComplete = false;

        while (!playerTurnComplete && !isCombatOver(combatInfo)) {
            AuditableEntity currentEntity = combatInfo.getTurnOrder().poll();

            if (currentEntity == null) {
                // Turn order exhausted, start new round
                combatInfo.setRoundNumber(combatInfo.getRoundNumber() + 1);
                repopulateTurnOrder(combatInfo);
                currentEntity = combatInfo.getTurnOrder().poll();
            }

            if (currentEntity instanceof PlayerCharacter) {
                processPlayerTurn(combatInfo);
                playerTurnComplete = true;
            } else if (currentEntity instanceof Monster monster) {
                processMonsterTurn(combatInfo, monster);
            }

            // Re-add entity to turn order if still alive
            if (isEntityAlive(currentEntity)) {
                combatInfo.getTurnOrder().offer(currentEntity);
            }
        }

        // Clean up defeated monsters
        combatInfo.getMonsters().removeIf(m -> m.getHealthPoints() <= 0);

        return combatInfo;
    }

    private void processPlayerTurn(CombatInformation combatInfo) {
        PlayerCharacter player = combatInfo.getPlayerCharacter();
        Monster target = combatInfo.getTargetMonster();

        switch (combatInfo.getCombatStatus()) {
            case ATTACK -> executePlayerAttack(player, target);
            case DEFEND -> executePlayerDefend(player);
            case CAST_SPELL -> executePlayerSpell(combatInfo);
            case USE_ITEM -> executePlayerItem(combatInfo);
            case FLEE -> executePlayerFlee(combatInfo);
            default -> log.warn("Unknown combat status: {}", combatInfo.getCombatStatus());
        }

        // Reset defend status after turn
        if (combatInfo.getCombatStatus() != CombatStatus.DEFEND) {
            // Remove any defend buffs if they were previously applied
            player.setStamina(player.getStamina()); // Placeholder for buff system
        }
    }

    private void processMonsterTurn(CombatInformation combatInfo, Monster monster) {
        PlayerCharacter player = combatInfo.getPlayerCharacter();

        // Simple AI: 80% attack, 20% special ability (if we implement that later)
        double action = Math.random();

        if (action < 0.8) {
            executeMonsterAttack(monster, player, combatInfo);
        } else {
            // Placeholder for special abilities
            executeMonsterAttack(monster, player, combatInfo);
        }
    }

    private void executePlayerAttack(PlayerCharacter player, Monster target) {
        if (target == null || target.getHealthPoints() <= 0) {
            log.warn("Invalid target for player attack");
            return;
        }

        int baseDamage = calculatePlayerDamage(player);
        boolean isCritical = Math.random() < CRITICAL_HIT_CHANCE;

        if (isCritical) {
            baseDamage = (int) (baseDamage * CRITICAL_HIT_MULTIPLIER);
            log.info("Critical hit! Damage: {}", baseDamage);
        }

        int actualDamage = Math.max(1, baseDamage - target.getDefense());
        target.setHealthPoints(Math.max(0, target.getHealthPoints() - actualDamage));

        log.info("Player deals {} damage to {}. Target HP: {}",
                actualDamage, target.getName(), target.getHealthPoints());
    }

    private void executePlayerDefend(PlayerCharacter player) {
        // Defending reduces incoming damage for the next turn
        // This could be implemented with a temporary buff system
        log.info("{} takes a defensive stance", player.getName());
        // In a real implementation, you'd set a flag or buff here
    }

    private void executePlayerSpell(CombatInformation combatInfo) {
        PlayerCharacter player = combatInfo.getPlayerCharacter();
        Spell spell = combatInfo.getSelectedSpell();
        Monster target = combatInfo.getTargetMonster();

        if (spell == null) {
            log.warn("No spell selected");
            return;
        }

        if (player.getMana() < spell.getManaCost()) {
            log.warn("Not enough mana to cast spell");
            return;
        }

        player.setMana(player.getMana() - spell.getManaCost());

        // Apply spell effects
        if (spell.getNegativeEffect() > 0 && target != null) {
            int spellDamage = spell.getNegativeEffect() + (player.getIntelligence() / 2);
            target.setHealthPoints(Math.max(0, target.getHealthPoints() - spellDamage));
            log.info("Spell {} deals {} damage to {}", spell.getName(), spellDamage, target.getName());
        }

        if (spell.getPositiveEffect() > 0) {
            int healing = spell.getPositiveEffect() + (player.getSpirit() / 2);
            player.setHealth(player.getHealth() + healing);
            log.info("Spell {} heals {} for {} HP", spell.getName(), player.getName(), healing);
        }
    }

    private void executePlayerItem(CombatInformation combatInfo) {
        PlayerCharacter player = combatInfo.getPlayerCharacter();
        Item item = combatInfo.getSelectedItem();

        if (item == null || !item.isConsumable()) {
            log.warn("Invalid or non-consumable item selected");
            return;
        }

        // Item effects would be defined in Item entity or a separate system
        // Placeholder for item effect application
        log.info("Player uses item: {}", item.getName());
    }

    private void executePlayerFlee(CombatInformation combatInfo) {
        PlayerCharacter player = combatInfo.getPlayerCharacter();

        // Flee chance based on agility vs average monster level
        double avgMonsterLevel = combatInfo.getMonsters().stream()
                .mapToInt(Monster::getLevel)
                .average()
                .orElse(1.0);

        int fleeChance = FLEE_SUCCESS_BASE_CHANCE +
                (player.getAgility() - (int)avgMonsterLevel * 5);

        boolean success = Math.random() * 100 < fleeChance;

        if (success) {
            log.info("Player successfully fled combat");
            combatInfo.getMonsters().clear(); // End combat
        } else {
            log.info("Failed to flee!");
        }
    }

    private void executeMonsterAttack(Monster monster, PlayerCharacter player,
                                      CombatInformation combatInfo) {
        int baseDamage = monster.getAttackPower();

        // Check if player is defending
        if (combatInfo.getCombatStatus() == CombatStatus.DEFEND) {
            baseDamage = baseDamage * (100 - DEFEND_REDUCTION_PERCENTAGE) / 100;
        }

        int actualDamage = Math.max(1, baseDamage - (player.getStamina() / 3));
        player.setHealth(Math.max(0, player.getHealth() - actualDamage));

        log.info("{} deals {} damage to {}. Player HP: {}",
                monster.getName(), actualDamage, player.getName(), player.getHealth());
    }

    private int calculatePlayerDamage(PlayerCharacter player) {
        // Base damage from strength, modified by level and agility
        return player.getStrength() + (player.getLevel() * 2) + (player.getAgility() / 3);
    }

    private boolean isCombatOver(CombatInformation combatInfo) {
        return combatInfo.getPlayerCharacter().getHealth() <= 0 ||
                combatInfo.getMonsters().isEmpty();
    }

    private boolean isEntityAlive(AuditableEntity entity) {
        if (entity instanceof PlayerCharacter pc) {
            return pc.getHealth() > 0;
        } else if (entity instanceof Monster monster) {
            return monster.getHealthPoints() > 0;
        }
        return false;
    }

    private void repopulateTurnOrder(CombatInformation combatInfo) {
        Queue<AuditableEntity> newOrder = new LinkedList<>();

        // Add player
        if (combatInfo.getPlayerCharacter().getHealth() > 0) {
            newOrder.offer(combatInfo.getPlayerCharacter());
        }

        // Add alive monsters
        combatInfo.getMonsters().stream()
                .filter(m -> m.getHealthPoints() > 0)
                .forEach(newOrder::offer);

        combatInfo.setTurnOrder(newOrder);
    }

    public CombatInformation initializeCombat(String playerId, List<String> monsterIds) {
        Queue<AuditableEntity> turnOrder = new LinkedList<>();
        PlayerCharacter player = playerCharacterRepository.findById(playerId)
                .orElseThrow(() -> new PlayerCharacterNotFoundException("Player Character with id of " + playerId + " not found"));
        List<Monster> monsters = monsterRepository.findAllById(monsterIds);

        // Simple turn order - could be enhanced with initiative/agility checks
        turnOrder.offer(player);
        monsters.forEach(turnOrder::offer);

        return new CombatInformation(
                player,
                new ArrayList<>(monsters),
                turnOrder,
                CombatStatus.ATTACK,
                1,
                null,
                null,
                !monsters.isEmpty() ? monsters.get(0) : null
        );
    }
}