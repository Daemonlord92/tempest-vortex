package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.NotBlank;

public record EquipItemRequest(
        @NotBlank(message = "Inventory item ID is required")
        String inventoryItemId
) {
}

