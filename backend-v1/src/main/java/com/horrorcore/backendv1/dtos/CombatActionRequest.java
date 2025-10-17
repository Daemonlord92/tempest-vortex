package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.dtos.enums.CombatStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CombatActionRequest(
        @NotNull(message = "Action is required")
        CombatStatus action,

        String targetMonsterId,
        String spellId,      // nullable
        String itemId        // nullable
) {
}
