package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.ApiResponse;
import com.horrorcore.backendv1.dtos.PlayerCharacterResponse;
import com.horrorcore.backendv1.dtos.PostNewCharacterRequest;
import com.horrorcore.backendv1.dtos.UpdateCharacterRequest;
import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.services.PlayerCharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/player")
@RequiredArgsConstructor
public class PlayerCharacterController {
    private final PlayerCharacterService playerCharacterService;

    @GetMapping("/characters")
    public ResponseEntity<ApiResponse<List<PlayerCharacterResponse>>> getAllCharacters() {
        List<PlayerCharacter> characters = playerCharacterService.getAllPlayerCharacters();
        List<PlayerCharacterResponse> response = characters.stream()
                .map(PlayerCharacterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<ApiResponse<PlayerCharacterResponse>> getCharacterById(@PathVariable String id) {
        PlayerCharacter character = playerCharacterService.getPlayerCharacterById(id);
        PlayerCharacterResponse response = PlayerCharacterResponse.from(character);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/characters")
    public ResponseEntity<ApiResponse<PlayerCharacterResponse>> createCharacter(
            @Valid @RequestBody PostNewCharacterRequest request) {
        PlayerCharacter character = playerCharacterService.createPlayerCharacter(request);
        PlayerCharacterResponse response = PlayerCharacterResponse.from(character);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Character created successfully", response));
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<ApiResponse<PlayerCharacterResponse>> updateCharacter(
            @PathVariable String id,
            @Valid @RequestBody UpdateCharacterRequest request) {
        PlayerCharacter character = playerCharacterService.updatePlayerCharacter(id, request);
        PlayerCharacterResponse response = PlayerCharacterResponse.from(character);

        return ResponseEntity.ok(ApiResponse.success("Character updated successfully", response));
    }

    @DeleteMapping("/characters/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCharacter(@PathVariable String id) {
        playerCharacterService.deletePlayerCharacter(id);

        return ResponseEntity.ok(ApiResponse.success("Character deleted successfully", null));
    }
}
