package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.*;

public record CreateMonsterRequest(
        @NotBlank(message = "Monster name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name,

        @NotBlank(message = "NPC role is required")
        @Pattern(regexp = "ENEMY|MERCHANT|QUEST_GIVER|TRAINER|GUARD|BOSS",
                message = "Invalid role. Must be one of: ENEMY, MERCHANT, QUEST_GIVER, TRAINER, GUARD, BOSS")
        String role,

        @Min(value = 1, message = "Level must be at least 1")
        @Max(value = 100, message = "Level cannot exceed 100")
        int level,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @Min(value = 0, message = "Health points cannot be negative")
        int healthPoints,

        @Min(value = 0, message = "Attack power cannot be negative")
        int attackPower,

        @Min(value = 0, message = "Defense cannot be negative")
        int defense,

        @NotBlank(message = "Habitat is required")
        @Pattern(regexp = "FOREST|MOUNTAIN|DESERT|SWAMP|CAVE|OCEAN|TUNDRA|URBAN|DUNGEON",
                message = "Invalid habitat. Must be one of: FOREST, MOUNTAIN, DESERT, SWAMP, CAVE, OCEAN, TUNDRA, URBAN, DUNGEON")
        String habitat,

        @Min(value = 0, message = "Experience points cannot be negative")
        int experiencePoints,

        @Min(value = 0, message = "Loot drop rate cannot be negative")
        @Max(value = 100, message = "Loot drop rate cannot exceed 100")
        int lootDropRate,

        String townId
) {
}

