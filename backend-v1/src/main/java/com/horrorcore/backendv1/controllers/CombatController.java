package com.horrorcore.backendv1.controllers;

import com.horrorcore.backendv1.dtos.CombatInformation;
import com.horrorcore.backendv1.dtos.CombatStateResponse;
import com.horrorcore.backendv1.dtos.StartCombatRequest;
import com.horrorcore.backendv1.services.CombatService;
import com.horrorcore.backendv1.services.CombatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/combat")
@RequiredArgsConstructor
public class CombatController {
    private final CombatService combatService;
    private final CombatSessionService sessionService;

//    @PostMapping("/start")
//    public ResponseEntity<CombatStateResponse> startCombat(@RequestBody StartCombatRequest request) {
//        // Delegate everything to the service
//        CombatInformation combat = combatService.initializeCombat(
//                request.playerCharacterId(),
//                request.monsterIds()
//        );
//
//        String sessionId = sessionService.createSession(combat);
//        return ResponseEntity.ok(toCombatStateResponse(sessionId, combat, "Combat started!"));
//    }
//    private CombatStateResponse toCombatStateResponse(String sessionId, CombatInformation combat, String message) {
//        return new CombatStateResponse(
//                sessionId,
//                combat,
//                message
//        );
//    }
}