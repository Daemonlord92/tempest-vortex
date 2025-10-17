package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateItemRequest;
import com.horrorcore.backendv1.dtos.UpdateItemRequest;
import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item getItemById(String id);

    Item createItem(CreateItemRequest request);

    Item updateItem(String id, UpdateItemRequest request);

    void deleteItem(String id);

    List<Item> getItemsByType(ItemType type);

    List<Item> getItemsByRarity(ItemRarity rarity);

    List<Item> getEquippableItems();

    List<Item> getConsumableItems();

    List<Item> searchItemsByName(String name);
}

