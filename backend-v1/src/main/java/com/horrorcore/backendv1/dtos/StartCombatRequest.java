package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record StartCombatRequest(
        @NotBlank(message = "Player character ID is required")
        String playerCharacterId,

        @NotEmpty(message = "At least one monster is required for combat")
        @Size(min = 1, max = 10, message = "Combat must have between 1 and 10 monsters")
        List<String> monsterIds
) {}
