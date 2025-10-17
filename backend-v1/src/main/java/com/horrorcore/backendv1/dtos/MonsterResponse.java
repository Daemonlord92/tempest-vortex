package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.enums.Habitat;
import com.horrorcore.backendv1.entities.enums.NPCRole;

import java.time.LocalDateTime;

public record MonsterResponse(
        String id,
        String name,
        NPCRole role,
        int level,
        String description,
        int healthPoints,
        int attackPower,
        int defense,
        Habitat habitat,
        int experiencePoints,
        int lootDropRate,
        String townId,
        String townName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static MonsterResponse from(Monster monster) {
        return new MonsterResponse(
                monster.getId(),
                monster.getName(),
                monster.getRole(),
                monster.getLevel(),
                monster.getDescription(),
                monster.getHealthPoints(),
                monster.getAttackPower(),
                monster.getDefense(),
                monster.getHabitat(),
                monster.getExperiencePoints(),
                monster.getLootDropRate(),
                monster.getTown() != null ? monster.getTown().getId() : null,
                monster.getTown() != null ? monster.getTown().getName() : null,
                monster.getCreatedAt(),
                monster.getUpdatedAt()
        );
    }
}

