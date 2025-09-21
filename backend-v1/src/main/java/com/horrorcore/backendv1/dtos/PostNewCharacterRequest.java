package com.horrorcore.backendv1.dtos;

public record PostNewCharacterRequest(
        String name,
        String playerClass
) {
}
