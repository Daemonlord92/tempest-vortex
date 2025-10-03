package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.PlayerCharacter;

import java.util.List;

public record CombatStateResponse(
        String sessionId,
        PlayerCharacter player,
        List<Monster> monsters,
        int roundNumber,
        boolean isOver,
        String message
) {}