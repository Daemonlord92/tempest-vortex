package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.dtos.enums.CombatStatus;

public record CombatActionRequest(
        String combatSessionId,
        CombatStatus action,
        String targetMonsterId,
        String spellId,      // nullable
        String itemId        // nullable
) {
    CombatActionRequest(String combatSessionId, CombatStatus action, String targetMonsterId) {
        this(combatSessionId, action, targetMonsterId, null, null);
    }
}
