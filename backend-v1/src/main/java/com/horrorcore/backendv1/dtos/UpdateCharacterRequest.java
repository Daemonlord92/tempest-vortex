package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.*;

public record UpdateCharacterRequest(
        @NotBlank(message = "Character name is required")
        @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
        String name,

        @NotBlank(message = "Player class is required")
        @Pattern(regexp = "WARRIOR|MAGE|ROGUE|CLERIC|RANGER|INVENTOR|ALCHEMIST|BARD|INQUISITOR",
                message = "Invalid player class")
        String playerClass,

        @Min(value = 1, message = "Level must be at least 1")
        @Max(value = 100, message = "Level cannot exceed 100")
        int level,

        @Min(value = 0, message = "Experience points cannot be negative")
        int experiencePoints,

        @Min(value = 1, message = "Health points must be at least 1")
        int healthPoints,

        @Min(value = 0, message = "Mana points cannot be negative")
        int manaPoints,

        @Min(value = 1, message = "Strength must be at least 1")
        int strength,

        @Min(value = 1, message = "Agility must be at least 1")
        int agility,

        @Min(value = 1, message = "Intelligence must be at least 1")
        int intelligence,

        @Min(value = 1, message = "Stamina must be at least 1")
        int stamina,

        @Min(value = 1, message = "Spirit must be at least 1")
        int spirit
) {
}
