package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateMonsterRequest;
import com.horrorcore.backendv1.dtos.UpdateMonsterRequest;
import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.enums.Habitat;

import java.util.List;

public interface MonsterService {

    List<Monster> getAllMonsters();

    Monster getMonsterById(String id);

    Monster createMonster(CreateMonsterRequest request);

    Monster updateMonster(String id, UpdateMonsterRequest request);

    void deleteMonster(String id);

    List<Monster> getMonstersByHabitat(Habitat habitat);

    List<Monster> getMonstersByLevel(int level);

    List<Monster> getMonstersByLevelRange(int minLevel, int maxLevel);

    List<Monster> getMonstersByTown(String townId);

    List<Monster> searchMonstersByName(String name);
}

