package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.entities.enums.PlayerClass;

import java.time.LocalDateTime;

public record PlayerCharacterResponse(
        String id,
        String name,
        PlayerClass playerClass,
        int level,
        int experience,
        int health,
        int mana,
        int strength,
        int agility,
        int intelligence,
        int stamina,
        int spirit,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PlayerCharacterResponse from(PlayerCharacter character) {
        return new PlayerCharacterResponse(
                character.getId(),
                character.getName(),
                character.getPlayerClass(),
                character.getLevel(),
                character.getExperience(),
                character.getHealth(),
                character.getMana(),
                character.getStrength(),
                character.getAgility(),
                character.getIntelligence(),
                character.getStamina(),
                character.getSpirit(),
                character.getCreatedAt(),
                character.getUpdatedAt()
        );
    }
}