package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddItemToInventoryRequest(
        @NotBlank(message = "Item ID is required")
        String itemId,

        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
) {
}

