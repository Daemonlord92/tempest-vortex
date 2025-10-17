package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.*;
import com.horrorcore.backendv1.entities.PlayerInventory;
import com.horrorcore.backendv1.services.PlayerInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/player/{playerId}/inventory")
@RequiredArgsConstructor
public class PlayerInventoryController {

    private final PlayerInventoryService inventoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlayerInventoryResponse>>> getInventory(
            @PathVariable String playerId) {

        List<PlayerInventory> inventory = inventoryService.getPlayerInventory(playerId);
        List<PlayerInventoryResponse> response = inventory.stream()
                .map(PlayerInventoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<PlayerInventoryResponse>> addItem(
            @PathVariable String playerId,
            @Valid @RequestBody AddItemToInventoryRequest request) {

        PlayerInventory inventory = inventoryService.addItemToInventory(
                playerId,
                request.itemId(),
                request.quantity()
        );

        PlayerInventoryResponse response = PlayerInventoryResponse.from(inventory);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Item added to inventory", response));
    }

    @DeleteMapping("/{inventoryItemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(
            @PathVariable String playerId,
            @PathVariable String inventoryItemId) {

        inventoryService.removeItemFromInventory(playerId, inventoryItemId);

        return ResponseEntity.ok(ApiResponse.success("Item removed from inventory", null));
    }

    @PostMapping("/equip")
    public ResponseEntity<ApiResponse<PlayerInventoryResponse>> equipItem(
            @PathVariable String playerId,
            @Valid @RequestBody EquipItemRequest request) {

        PlayerInventory inventory = inventoryService.equipItem(playerId, request.inventoryItemId());
        PlayerInventoryResponse response = PlayerInventoryResponse.from(inventory);

        return ResponseEntity.ok(ApiResponse.success("Item equipped", response));
    }

    @PostMapping("/unequip/{inventoryItemId}")
    public ResponseEntity<ApiResponse<PlayerInventoryResponse>> unequipItem(
            @PathVariable String playerId,
            @PathVariable String inventoryItemId) {

        PlayerInventory inventory = inventoryService.unequipItem(playerId, inventoryItemId);
        PlayerInventoryResponse response = PlayerInventoryResponse.from(inventory);

        return ResponseEntity.ok(ApiResponse.success("Item unequipped", response));
    }

    @PostMapping("/consume")
    public ResponseEntity<ApiResponse<PlayerInventoryResponse>> consumeItem(
            @PathVariable String playerId,
            @Valid @RequestBody ConsumeItemRequest request) {

        PlayerInventory inventory = inventoryService.consumeItem(
                playerId,
                request.inventoryItemId(),
                request.quantity()
        );

        if (inventory == null) {
            return ResponseEntity.ok(ApiResponse.success("Item consumed and removed (quantity reached 0)", null));
        }

        PlayerInventoryResponse response = PlayerInventoryResponse.from(inventory);
        return ResponseEntity.ok(ApiResponse.success("Item consumed", response));
    }

    @GetMapping("/equipped")
    public ResponseEntity<ApiResponse<List<PlayerInventoryResponse>>> getEquippedItems(
            @PathVariable String playerId) {

        List<PlayerInventory> equipped = inventoryService.getEquippedItems(playerId);
        List<PlayerInventoryResponse> response = equipped.stream()
                .map(PlayerInventoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/consumables")
    public ResponseEntity<ApiResponse<List<PlayerInventoryResponse>>> getConsumableItems(
            @PathVariable String playerId) {

        List<PlayerInventory> consumables = inventoryService.getConsumableItems(playerId);
        List<PlayerInventoryResponse> response = consumables.stream()
                .map(PlayerInventoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

