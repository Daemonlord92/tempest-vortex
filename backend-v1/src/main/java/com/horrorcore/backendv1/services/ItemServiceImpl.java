package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateItemRequest;
import com.horrorcore.backendv1.dtos.UpdateItemRequest;
import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;
import com.horrorcore.backendv1.exceptions.ResourceNotFoundException;
import com.horrorcore.backendv1.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        log.info("Fetching all items");
        return itemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item getItemById(String id) {
        log.info("Fetching item with id: {}", id);
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    @Transactional
    public Item createItem(CreateItemRequest request) {
        log.info("Creating new item: {}", request.name());

        Item item = new Item();
        item.setName(request.name());
        item.setType(ItemType.valueOf(request.type()));
        item.setLevelRequirement(request.levelRequirement());
        item.setEquippable(request.isEquippable());
        item.setConsumable(request.isConsumable());
        item.setUnique(request.isUnique());
        item.setTradable(request.isTradable());
        item.setStackable(request.isStackable());
        item.setMaxStackSize(request.maxStackSize());
        item.setValue(request.value());
        item.setDescription(request.description());
        item.setRarity(ItemRarity.valueOf(request.rarity()));

        Item savedItem = itemRepository.save(item);
        log.info("Created item with id: {}", savedItem.getId());

        return savedItem;
    }

    @Override
    @Transactional
    public Item updateItem(String id, UpdateItemRequest request) {
        log.info("Updating item with id: {}", id);

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        item.setName(request.name());
        item.setType(ItemType.valueOf(request.type()));
        item.setLevelRequirement(request.levelRequirement());
        item.setEquippable(request.isEquippable());
        item.setConsumable(request.isConsumable());
        item.setUnique(request.isUnique());
        item.setTradable(request.isTradable());
        item.setStackable(request.isStackable());
        item.setMaxStackSize(request.maxStackSize());
        item.setValue(request.value());
        item.setDescription(request.description());
        item.setRarity(ItemRarity.valueOf(request.rarity()));

        Item updatedItem = itemRepository.save(item);
        log.info("Updated item with id: {}", updatedItem.getId());

        return updatedItem;
    }

    @Override
    @Transactional
    public void deleteItem(String id) {
        log.info("Deleting item with id: {}", id);

        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item", "id", id);
        }

        itemRepository.deleteById(id);
        log.info("Deleted item with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByType(ItemType type) {
        log.info("Fetching items by type: {}", type);
        return itemRepository.findAll().stream()
                .filter(item -> item.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByRarity(ItemRarity rarity) {
        log.info("Fetching items by rarity: {}", rarity);
        return itemRepository.findAll().stream()
                .filter(item -> item.getRarity() == rarity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getEquippableItems() {
        log.info("Fetching all equippable items");
        return itemRepository.findAll().stream()
                .filter(Item::isEquippable)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getConsumableItems() {
        log.info("Fetching all consumable items");
        return itemRepository.findAll().stream()
                .filter(Item::isConsumable)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> searchItemsByName(String name) {
        log.info("Searching items by name: {}", name);
        return itemRepository.findAll().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}

