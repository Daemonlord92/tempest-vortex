package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.*;

public record UpdateItemRequest(
        @NotBlank(message = "Item name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name,

        @NotBlank(message = "Item type is required")
        @Pattern(regexp = "WEAPON|ARMOR|CONSUMABLE|MATERIAL|QUEST|MISC",
                message = "Invalid item type. Must be one of: WEAPON, ARMOR, CONSUMABLE, MATERIAL, QUEST, MISC")
        String type,

        @Min(value = 1, message = "Level requirement must be at least 1")
        @Max(value = 100, message = "Level requirement cannot exceed 100")
        int levelRequirement,

        boolean isEquippable,

        boolean isConsumable,

        boolean isUnique,

        boolean isTradable,

        boolean isStackable,

        @Min(value = 1, message = "Max stack size must be at least 1")
        int maxStackSize,

        @Min(value = 0, message = "Item value cannot be negative")
        int value,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotBlank(message = "Item rarity is required")
        @Pattern(regexp = "JUNK|COMMON|UNCOMMON|RARE|EPIC|LEGENDARY|MYTHIC",
                message = "Invalid rarity. Must be one of: JUNK, COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC")
        String rarity
) {
}
