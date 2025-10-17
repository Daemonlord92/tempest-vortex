package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.entities.Monster;
import com.horrorcore.backendv1.entities.PlayerCharacter;

import java.util.List;

public record CombatResponse(
        String sessionId,
        PlayerCharacter player,
        List<Monster> monsters,
        int roundNumber,
        boolean isOver,
        String winner,
        String message
) {
    public static CombatResponse fromCombatInformation(String sessionId, CombatInformation combat, String message) {
        boolean isOver = combat.getPlayerCharacter().getHealth() <= 0 || combat.getMonsters().isEmpty();
        String winner = null;

        if (isOver) {
            winner = combat.getPlayerCharacter().getHealth() > 0 ? "Player" : "Monsters";
        }

        return new CombatResponse(
                sessionId,
                combat.getPlayerCharacter(),
                combat.getMonsters(),
                combat.getRoundNumber(),
                isOver,
                winner,
                message
        );
    }
}
