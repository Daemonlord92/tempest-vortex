package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.entities.PlayerInventory;

import java.util.List;

public interface PlayerInventoryService {

    /**
     * Get all inventory items for a player
     */
    List<PlayerInventory> getPlayerInventory(String playerId);

    /**
     * Add an item to a player's inventory
     */
    PlayerInventory addItemToInventory(String playerId, String itemId, int quantity);

    /**
     * Remove an item from a player's inventory
     */
    void removeItemFromInventory(String playerId, String inventoryItemId);

    /**
     * Equip an item
     */
    PlayerInventory equipItem(String playerId, String inventoryItemId);

    /**
     * Unequip an item
     */
    PlayerInventory unequipItem(String playerId, String inventoryItemId);

    /**
     * Consume an item (for potions, food, etc.)
     */
    PlayerInventory consumeItem(String playerId, String inventoryItemId, int quantity);

    /**
     * Get all equipped items for a player
     */
    List<PlayerInventory> getEquippedItems(String playerId);

    /**
     * Get all consumable items for a player
     */
    List<PlayerInventory> getConsumableItems(String playerId);

    /**
     * Check if player has an item
     */
    boolean hasItem(String playerId, String itemId);
}
