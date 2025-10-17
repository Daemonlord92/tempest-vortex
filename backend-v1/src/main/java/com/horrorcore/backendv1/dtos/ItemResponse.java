package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;

import java.time.LocalDateTime;

public record ItemResponse(
        String id,
        String name,
        ItemType type,
        int levelRequirement,
        boolean isEquippable,
        boolean isConsumable,
        boolean isUnique,
        boolean isTradable,
        boolean isStackable,
        int maxStackSize,
        int value,
        String description,
        ItemRarity rarity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getLevelRequirement(),
                item.isEquippable(),
                item.isConsumable(),
                item.isUnique(),
                item.isTradable(),
                item.isStackable(),
                item.getMaxStackSize(),
                item.getValue(),
                item.getDescription(),
                item.getRarity(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}

