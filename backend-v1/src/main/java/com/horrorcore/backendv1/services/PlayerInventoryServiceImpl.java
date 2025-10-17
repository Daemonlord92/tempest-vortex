package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.entities.PlayerInventory;
import com.horrorcore.backendv1.exceptions.InvalidRequestException;
import com.horrorcore.backendv1.exceptions.PlayerCharacterNotFoundException;
import com.horrorcore.backendv1.exceptions.ResourceNotFoundException;
import com.horrorcore.backendv1.repositories.ItemRepository;
import com.horrorcore.backendv1.repositories.PlayerCharacterRepository;
import com.horrorcore.backendv1.repositories.PlayerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerInventoryServiceImpl implements PlayerInventoryService {

    private final PlayerInventoryRepository inventoryRepository;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PlayerInventory> getPlayerInventory(String playerId) {
        // Verify player exists
        if (!playerCharacterRepository.existsById(playerId)) {
            throw new PlayerCharacterNotFoundException("Player with id: " + playerId + " not found");
        }
        return inventoryRepository.findByPlayerCharacterId(playerId);
    }

    @Override
    @Transactional
    public PlayerInventory addItemToInventory(String playerId, String itemId, int quantity) {
        // Verify player exists
        PlayerCharacter player = playerCharacterRepository.findById(playerId)
                .orElseThrow(() -> new PlayerCharacterNotFoundException("Player with id: " + playerId + " not found"));

        // Verify item exists
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        // Check if player already has this item
        Optional<PlayerInventory> existingInventory =
                inventoryRepository.findByPlayerCharacterIdAndItemId(playerId, itemId);

        if (existingInventory.isPresent()) {
            // If item is stackable, increase quantity
            if (item.isStackable()) {
                PlayerInventory inventory = existingInventory.get();
                inventory.increaseQuantity(quantity);
                log.info("Increased quantity of item {} for player {} by {}",
                        item.getName(), player.getName(), quantity);
                return inventoryRepository.save(inventory);
            } else {
                throw new InvalidRequestException("Player already has this non-stackable item");
            }
        } else {
            // Create new inventory entry
            PlayerInventory newInventory = PlayerInventory.builder()
                    .playerCharacter(player)
                    .item(item)
                    .quantity(quantity)
                    .isEquipped(false)
                    .build();

            log.info("Added item {} to player {}'s inventory", item.getName(), player.getName());
            return inventoryRepository.save(newInventory);
        }
    }

    @Override
    @Transactional
    public void removeItemFromInventory(String playerId, String inventoryItemId) {
        PlayerInventory inventory = findInventoryItem(playerId, inventoryItemId);

        log.info("Removing item {} from player {}'s inventory",
                inventory.getItem().getName(), inventory.getPlayerCharacter().getName());
        inventoryRepository.delete(inventory);
    }

    @Override
    @Transactional
    public PlayerInventory equipItem(String playerId, String inventoryItemId) {
        PlayerInventory inventory = findInventoryItem(playerId, inventoryItemId);

        if (!inventory.canBeEquipped()) {
            throw new InvalidRequestException("This item cannot be equipped");
        }

        // TODO: Check if player already has an item equipped in the same slot
        // For now, just equip it
        inventory.equip();

        log.info("Player {} equipped item {}",
                inventory.getPlayerCharacter().getName(), inventory.getItem().getName());
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public PlayerInventory unequipItem(String playerId, String inventoryItemId) {
        PlayerInventory inventory = findInventoryItem(playerId, inventoryItemId);

        if (!inventory.isEquipped()) {
            throw new InvalidRequestException("This item is not equipped");
        }

        inventory.unequip();

        log.info("Player {} unequipped item {}",
                inventory.getPlayerCharacter().getName(), inventory.getItem().getName());
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public PlayerInventory consumeItem(String playerId, String inventoryItemId, int quantity) {
        PlayerInventory inventory = findInventoryItem(playerId, inventoryItemId);

        if (!inventory.canBeConsumed()) {
            throw new InvalidRequestException("This item cannot be consumed or quantity is 0");
        }

        if (inventory.getQuantity() < quantity) {
            throw new InvalidRequestException(
                    String.format("Not enough items. Has: %d, Requested: %d",
                            inventory.getQuantity(), quantity));
        }

        // Decrease quantity
        for (int i = 0; i < quantity; i++) {
            inventory.consume();
        }

        log.info("Player {} consumed {} x{}",
                inventory.getPlayerCharacter().getName(),
                inventory.getItem().getName(),
                quantity);

        // If quantity reaches 0, remove from inventory
        if (inventory.getQuantity() == 0) {
            inventoryRepository.delete(inventory);
            return null;
        }

        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerInventory> getEquippedItems(String playerId) {
        // Verify player exists
        if (!playerCharacterRepository.existsById(playerId)) {
            throw new PlayerCharacterNotFoundException("Player with id: " + playerId + " not found");
        }
        return inventoryRepository.findByPlayerCharacterIdAndIsEquippedTrue(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerInventory> getConsumableItems(String playerId) {
        // Verify player exists
        if (!playerCharacterRepository.existsById(playerId)) {
            throw new PlayerCharacterNotFoundException("Player with id: " + playerId + " not found");
        }
        return inventoryRepository.findConsumableItemsByPlayerId(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasItem(String playerId, String itemId) {
        return inventoryRepository.existsByPlayerCharacterIdAndItemId(playerId, itemId);
    }

    // Helper method to find and validate inventory item
    private PlayerInventory findInventoryItem(String playerId, String inventoryItemId) {
        PlayerInventory inventory = inventoryRepository.findById(inventoryItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item", "id", inventoryItemId));

        // Verify it belongs to the player
        if (!inventory.getPlayerCharacter().getId().equals(playerId)) {
            throw new InvalidRequestException("This inventory item does not belong to the player");
        }

        return inventory;
    }
}

