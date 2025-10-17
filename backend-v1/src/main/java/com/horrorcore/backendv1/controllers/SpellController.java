package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.*;
import com.horrorcore.backendv1.entities.Spell;
import com.horrorcore.backendv1.services.SpellService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/spells")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SpellResponse>>> getAllSpells() {
        List<Spell> spells = spellService.getAllSpells();
        List<SpellResponse> response = spells.stream()
                .map(SpellResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SpellResponse>> getSpellById(@PathVariable String id) {
        Spell spell = spellService.getSpellById(id);
        SpellResponse response = SpellResponse.from(spell);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SpellResponse>> createSpell(
            @Valid @RequestBody CreateSpellRequest request) {
        Spell spell = spellService.createSpell(request);
        SpellResponse response = SpellResponse.from(spell);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Spell created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SpellResponse>> updateSpell(
            @PathVariable String id,
            @Valid @RequestBody UpdateSpellRequest request) {
        Spell spell = spellService.updateSpell(id, request);
        SpellResponse response = SpellResponse.from(spell);

        return ResponseEntity.ok(ApiResponse.success("Spell updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSpell(@PathVariable String id) {
        spellService.deleteSpell(id);

        return ResponseEntity.ok(ApiResponse.success("Spell deleted successfully", null));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<ApiResponse<List<SpellResponse>>> getSpellsByLevel(
            @PathVariable int level) {
        List<Spell> spells = spellService.getSpellsByPlayerLevel(level);
        List<SpellResponse> response = spells.stream()
                .map(SpellResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Spells available for level " + level, response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SpellResponse>>> searchSpells(
            @RequestParam String name) {
        List<Spell> spells = spellService.searchSpellsByName(name);
        List<SpellResponse> response = spells.stream()
                .map(SpellResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.success("Search results for: " + name, response));
    }
}

