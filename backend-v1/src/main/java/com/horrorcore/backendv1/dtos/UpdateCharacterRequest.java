package com.horrorcore.backendv1.dtos;

public record UpdateCharacterRequest(
        String name,
        String playerClass,
        int level,
        int experiencePoints,
        int healthPoints,
        int manaPoints,
        int strength,
        int agility,
        int intelligence,
        int stamina,
        int spirit
) {
}
