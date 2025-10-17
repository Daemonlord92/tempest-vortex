package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.*;
import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.enums.Habitat;
import com.horrorcore.backendv1.services.MonsterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> getAllMonsters() {
        List<Monster> monsters = monsterService.getAllMonsters();
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MonsterResponse>> getMonsterById(@PathVariable String id) {
        Monster monster = monsterService.getMonsterById(id);
        MonsterResponse response = MonsterResponse.from(monster);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MonsterResponse>> createMonster(
            @Valid @RequestBody CreateMonsterRequest request) {
        Monster monster = monsterService.createMonster(request);
        MonsterResponse response = MonsterResponse.from(monster);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Monster created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MonsterResponse>> updateMonster(
            @PathVariable String id,
            @Valid @RequestBody UpdateMonsterRequest request) {
        Monster monster = monsterService.updateMonster(id, request);
        MonsterResponse response = MonsterResponse.from(monster);

        return ResponseEntity.ok(ApiResponse.success("Monster updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMonster(@PathVariable String id) {
        monsterService.deleteMonster(id);

        return ResponseEntity.ok(ApiResponse.success("Monster deleted successfully", null));
    }

    @GetMapping("/habitat/{habitat}")
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> getMonstersByHabitat(
            @PathVariable Habitat habitat) {
        List<Monster> monsters = monsterService.getMonstersByHabitat(habitat);
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Monsters in habitat: " + habitat.name(), response));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> getMonstersByLevel(
            @PathVariable int level) {
        List<Monster> monsters = monsterService.getMonstersByLevel(level);
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Monsters at level " + level, response));
    }

    @GetMapping("/level-range")
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> getMonstersByLevelRange(
            @RequestParam int minLevel,
            @RequestParam int maxLevel) {
        List<Monster> monsters = monsterService.getMonstersByLevelRange(minLevel, maxLevel);
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Monsters between levels " + minLevel + " and " + maxLevel, response));
    }

    @GetMapping("/town/{townId}")
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> getMonstersByTown(
            @PathVariable String townId) {
        List<Monster> monsters = monsterService.getMonstersByTown(townId);
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Monsters in town", response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MonsterResponse>>> searchMonsters(
            @RequestParam String name) {
        List<Monster> monsters = monsterService.searchMonstersByName(name);
        List<MonsterResponse> response = monsters.stream()
                .map(MonsterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Search results for: " + name, response));
    }
}

