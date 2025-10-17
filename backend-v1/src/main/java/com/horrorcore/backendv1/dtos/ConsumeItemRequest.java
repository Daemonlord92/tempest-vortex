package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ConsumeItemRequest(
        @NotBlank(message = "Inventory item ID is required")
        String inventoryItemId,

        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
) {
}


