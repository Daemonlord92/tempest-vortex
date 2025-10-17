package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.*;

public record UpdateSpellRequest(
        @NotBlank(message = "Spell name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @Min(value = 0, message = "Mana cost cannot be negative")
        int manaCost,

        @Min(value = 0, message = "Cooldown cannot be negative")
        int cooldown,

        @Min(value = 1, message = "Level requirement must be at least 1")
        @Max(value = 100, message = "Level requirement cannot exceed 100")
        int levelRequirement,

        @Min(value = 0, message = "Positive effect cannot be negative")
        int positiveEffect,

        @Min(value = 0, message = "Negative effect cannot be negative")
        int negativeEffect,

        @NotBlank(message = "Effect type is required")
        @Pattern(regexp = "DAMAGE|HEALING|BUFF|DEBUFF|UTILITY",
                message = "Invalid effect type. Must be one of: DAMAGE, HEALING, BUFF, DEBUFF, UTILITY")
        String effectType
) {
}

