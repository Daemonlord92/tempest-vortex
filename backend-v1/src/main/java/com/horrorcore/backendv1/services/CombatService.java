package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CombatInformation;
import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.repositories.MonsterRepository;
import com.horrorcore.backendv1.repositories.PlayerCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatService {
    private final PlayerCharacterRepository playerCharacterRepository;
    private final MonsterRepository monsterRepository;

    public CombatInformation processCombatTurn(CombatInformation combatInfo) {
        do {
            var currentEntity = combatInfo.getTurnOrder().poll();
            if (currentEntity.equals(combatInfo.getPlayerCharacter())) {
                // Player's turn logic here
                // For simplicity, let's assume the player always attacks the first monster
                if (!combatInfo.getMonsters().isEmpty()) {
                    var targetMonster = combatInfo.getMonsters().get(0);
                    switch (combatInfo.getCombatStatus()){
                        case ATTACK:
                            targetMonster.setHealthPoints(targetMonster.getHealthPoints() - (combatInfo.getPlayerCharacter().getStrength() / 2));
                            break;
                        case DEFEND:

                    }
                    if (targetMonster.getHealthPoints() <= 0) {
                        combatInfo.getMonsters().remove(targetMonster);
                    }
                }
            } else {
                // Monster's turn logic here
                if (currentEntity instanceof Monster monster && combatInfo.getPlayerCharacter().getHealth() > 0) {
                    monster.setHealthPoints(monster.getHealthPoints() - (combatInfo.getPlayerCharacter().getAgility() / 4));
                    if (monster.getHealthPoints() <= 0) {
                        combatInfo.getMonsters().remove(monster);
                    }
                }
            }
            // Re-add the entity to the end of the turn order if still alive
            if ((currentEntity instanceof Monster monster && monster.getHealthPoints() > 0)
                    || (currentEntity instanceof PlayerCharacter pc && pc.getHealth() > 0)) {
                combatInfo.getTurnOrder().offer(currentEntity);
            }

        } while (combatInfo.getTurnOrder().peek().equals(combatInfo.getPlayerCharacter()));
        return combatInfo;
    }
}
