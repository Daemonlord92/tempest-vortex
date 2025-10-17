package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.PlayerInventory;

import java.time.LocalDateTime;

public record PlayerInventoryResponse(
        String id,
        String itemId,
        String itemName,
        String itemType,
        String itemRarity,
        int quantity,
        boolean isEquipped,
        boolean canBeEquipped,
        boolean canBeConsumed,
        LocalDateTime acquiredAt
) {
    public static PlayerInventoryResponse from(PlayerInventory inventory) {
        Item item = inventory.getItem();
        return new PlayerInventoryResponse(
                inventory.getId(),
                item.getId(),
                item.getName(),
                item.getType().name(),
                item.getRarity().name(),
                inventory.getQuantity(),
                inventory.isEquipped(),
                inventory.canBeEquipped(),
                inventory.canBeConsumed(),
                inventory.getAcquiredAt()
        );
    }
}

