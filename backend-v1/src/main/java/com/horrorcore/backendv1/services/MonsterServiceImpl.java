package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateMonsterRequest;
import com.horrorcore.backendv1.dtos.UpdateMonsterRequest;
import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.Town;
import com.horrorcore.backendv1.entities.enums.Habitat;
import com.horrorcore.backendv1.entities.enums.NPCRole;
import com.horrorcore.backendv1.exceptions.ResourceNotFoundException;
import com.horrorcore.backendv1.repositories.MonsterRepository;
import com.horrorcore.backendv1.repositories.TownRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {

    private final MonsterRepository monsterRepository;
    private final TownRepository townRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Monster> getAllMonsters() {
        log.info("Fetching all monsters");
        return monsterRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Monster getMonsterById(String id) {
        log.info("Fetching monster with id: {}", id);
        return monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monster", "id", id));
    }

    @Override
    @Transactional
    public Monster createMonster(CreateMonsterRequest request) {
        log.info("Creating new monster: {}", request.name());

        Monster monster = new Monster();
        monster.setName(request.name());
        monster.setRole(NPCRole.valueOf(request.role()));
        monster.setLevel(request.level());
        monster.setDescription(request.description());
        monster.setHealthPoints(request.healthPoints());
        monster.setAttackPower(request.attackPower());
        monster.setDefense(request.defense());
        monster.setHabitat(Habitat.valueOf(request.habitat()));
        monster.setExperiencePoints(request.experiencePoints());
        monster.setLootDropRate(request.lootDropRate());

        if (request.townId() != null) {
            Town town = townRepository.findById(request.townId())
                    .orElseThrow(() -> new ResourceNotFoundException("Town", "id", request.townId()));
            monster.setTown(town);
        }

        Monster savedMonster = monsterRepository.save(monster);
        log.info("Created monster with id: {}", savedMonster.getId());

        return savedMonster;
    }

    @Override
    @Transactional
    public Monster updateMonster(String id, UpdateMonsterRequest request) {
        log.info("Updating monster with id: {}", id);

        Monster monster = monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monster", "id", id));

        monster.setName(request.name());
        monster.setRole(NPCRole.valueOf(request.role()));
        monster.setLevel(request.level());
        monster.setDescription(request.description());
        monster.setHealthPoints(request.healthPoints());
        monster.setAttackPower(request.attackPower());
        monster.setDefense(request.defense());
        monster.setHabitat(Habitat.valueOf(request.habitat()));
        monster.setExperiencePoints(request.experiencePoints());
        monster.setLootDropRate(request.lootDropRate());

        if (request.townId() != null) {
            Town town = townRepository.findById(request.townId())
                    .orElseThrow(() -> new ResourceNotFoundException("Town", "id", request.townId()));
            monster.setTown(town);
        } else {
            monster.setTown(null);
        }

        Monster updatedMonster = monsterRepository.save(monster);
        log.info("Updated monster with id: {}", updatedMonster.getId());

        return updatedMonster;
    }

    @Override
    @Transactional
    public void deleteMonster(String id) {
        log.info("Deleting monster with id: {}", id);

        if (!monsterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Monster", "id", id);
        }

        monsterRepository.deleteById(id);
        log.info("Deleted monster with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monster> getMonstersByHabitat(Habitat habitat) {
        log.info("Fetching monsters by habitat: {}", habitat);
        return monsterRepository.findAll().stream()
                .filter(monster -> monster.getHabitat() == habitat)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monster> getMonstersByLevel(int level) {
        log.info("Fetching monsters by level: {}", level);
        return monsterRepository.findAll().stream()
                .filter(monster -> monster.getLevel() == level)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monster> getMonstersByLevelRange(int minLevel, int maxLevel) {
        log.info("Fetching monsters by level range: {} to {}", minLevel, maxLevel);
        return monsterRepository.findAll().stream()
                .filter(monster -> monster.getLevel() >= minLevel && monster.getLevel() <= maxLevel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monster> getMonstersByTown(String townId) {
        log.info("Fetching monsters by town id: {}", townId);
        return monsterRepository.findAll().stream()
                .filter(monster -> monster.getTown() != null && monster.getTown().getId().equals(townId))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monster> searchMonstersByName(String name) {
        log.info("Searching monsters by name: {}", name);
        return monsterRepository.findAll().stream()
                .filter(monster -> monster.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}

