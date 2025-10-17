package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.*;
import com.horrorcore.backendv1.entities.Item;
import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;
import com.horrorcore.backendv1.services.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponse>> getItemById(@PathVariable String id) {
        Item item = itemService.getItemById(id);
        ItemResponse response = ItemResponse.from(item);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponse>> createItem(
            @Valid @RequestBody CreateItemRequest request) {
        Item item = itemService.createItem(request);
        ItemResponse response = ItemResponse.from(item);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Item created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponse>> updateItem(
            @PathVariable String id,
            @Valid @RequestBody UpdateItemRequest request) {
        Item item = itemService.updateItem(id, request);
        ItemResponse response = ItemResponse.from(item);

        return ResponseEntity.ok(ApiResponse.success("Item updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);

        return ResponseEntity.ok(ApiResponse.success("Item deleted successfully", null));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getItemsByType(
            @PathVariable ItemType type) {
        List<Item> items = itemService.getItemsByType(type);
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Items of type: " + type.getDisplayName(), response));
    }

    @GetMapping("/rarity/{rarity}")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getItemsByRarity(
            @PathVariable ItemRarity rarity) {
        List<Item> items = itemService.getItemsByRarity(rarity);
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Items of rarity: " + rarity.getDisplayName(), response));
    }

    @GetMapping("/equippable")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getEquippableItems() {
        List<Item> items = itemService.getEquippableItems();
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("All equippable items", response));
    }

    @GetMapping("/consumable")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getConsumableItems() {
        List<Item> items = itemService.getConsumableItems();
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("All consumable items", response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> searchItems(
            @RequestParam String name) {
        List<Item> items = itemService.searchItemsByName(name);
        List<ItemResponse> response = items.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Search results for: " + name, response));
    }
}

