package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Spell;
import com.horrorcore.backendv1.entities.enums.SpellEffectType;

import java.time.LocalDateTime;

public record SpellResponse(
        String id,
        String name,
        String description,
        int manaCost,
        int cooldown,
        int levelRequirement,
        int positiveEffect,
        int negativeEffect,
        SpellEffectType effectType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SpellResponse from(Spell spell) {
        return new SpellResponse(
                spell.getId(),
                spell.getName(),
                spell.getDescription(),
                spell.getManaCost(),
                spell.getCooldown(),
                spell.getLevelRequirement(),
                spell.getPositiveEffect(),
                spell.getNegativeEffect(),
                spell.getEffectType(),
                spell.getCreatedAt(),
                spell.getUpdatedAt()
        );
    }
}

