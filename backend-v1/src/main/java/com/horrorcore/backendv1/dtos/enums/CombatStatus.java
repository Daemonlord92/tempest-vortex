package com.horrorcore.backendv1.dtos.enums;

public enum CombatStatus {
    ATTACK("Attack"),
    DEFEND("Defend"),
    FLEE("Flee"),
    USE_ITEM("Use Item"),
    CAST_SPELL("Cast Spell");
    private final String displayName;
    CombatStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
