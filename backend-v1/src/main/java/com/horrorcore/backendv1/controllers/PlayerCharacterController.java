package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.services.PlayerCharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerCharacterController {
    private final PlayerCharacterService playerCharacterService;

    public PlayerCharacterController(PlayerCharacterService playerCharacterService) {
        this.playerCharacterService = playerCharacterService;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<PlayerCharacter>> getAllCharacters() {
        return ResponseEntity.ok(playerCharacterService.getAllPlayerCharacters());
    }
}
