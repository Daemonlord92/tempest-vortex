package com.horrorcore.backendv1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PostNewCharacterRequest(
        @NotBlank(message = "Character name is required")
        @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
        String name,

        @NotBlank(message = "Player class is required")
        @Pattern(regexp = "WARRIOR|MAGE|ROGUE|CLERIC|RANGER|INVENTOR|ALCHEMIST|BARD|INQUISITOR",
                message = "Invalid player class. Must be one of: WARRIOR, MAGE, ROGUE, CLERIC, RANGER, INVENTOR, ALCHEMIST, BARD, INQUISITOR")
        String playerClass
) {
}
