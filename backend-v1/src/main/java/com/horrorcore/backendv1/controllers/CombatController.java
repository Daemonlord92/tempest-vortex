package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.*;
import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.services.CombatService;
import com.horrorcore.backendv1.services.CombatSessionService;
import com.horrorcore.backendv1.utils.CombatMessageHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/combat")
@RequiredArgsConstructor
public class CombatController {
    private final CombatService combatService;
    private final CombatSessionService sessionService;

    @PostMapping("/start")
    public ResponseEntity<ApiResponse<CombatResponse>> startCombat(@Valid @RequestBody StartCombatRequest request) {
        // Initialize combat
        CombatInformation combat = combatService.initializeCombat(
                request.playerCharacterId(),
                request.monsterIds()
        );

        // Create session
        String sessionId = sessionService.createSession(combat);

        // Build response
        CombatResponse response = CombatResponse.fromCombatInformation(sessionId, combat, "Combat started!");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Combat initiated successfully", response));
    }

    @PostMapping("/{sessionId}/action")
    public ResponseEntity<ApiResponse<CombatResponse>> takeCombatAction(
            @PathVariable String sessionId,
            @Valid @RequestBody CombatActionRequest request) {

        // Get combat session
        CombatInformation combat = sessionService.getSession(sessionId);
        if (combat == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Combat session not found"));
        }

        // Update combat state with player action
        combat.setCombatStatus(request.action());

        if (request.targetMonsterId() != null) {
            Monster target = combat.getMonsters().stream()
                    .filter(m -> m.getId().equals(request.targetMonsterId()))
                    .findFirst()
                    .orElse(null);
            combat.setTargetMonster(target);
        }

        // Process combat turn
        combat = combatService.processCombatTurn(combat);

        // Update session
        sessionService.createSession(combat); // Updates existing session

        // Build response message using utility class
        String message = CombatMessageHelper.buildActionMessage(request.action(), combat);
        CombatResponse response = CombatResponse.fromCombatInformation(sessionId, combat, message);

        // Check if combat is over
        if (response.isOver()) {
            sessionService.endSession(sessionId);
        }

        return ResponseEntity.ok(ApiResponse.success(message, response));
    }

    @GetMapping("/{sessionId}/state")
    public ResponseEntity<ApiResponse<CombatResponse>> getCombatState(@PathVariable String sessionId) {
        CombatInformation combat = sessionService.getSession(sessionId);

        if (combat == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Combat session not found"));
        }

        CombatResponse response = CombatResponse.fromCombatInformation(
                sessionId,
                combat,
                "Current combat state"
        );

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/{sessionId}/end")
    public ResponseEntity<ApiResponse<Void>> endCombat(@PathVariable String sessionId) {
        CombatInformation combat = sessionService.getSession(sessionId);

        if (combat == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Combat session not found"));
        }

        sessionService.endSession(sessionId);

        return ResponseEntity.ok(ApiResponse.success("Combat session ended", null));
    }
}