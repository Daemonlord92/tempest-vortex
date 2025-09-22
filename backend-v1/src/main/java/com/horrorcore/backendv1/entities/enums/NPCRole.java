package com.horrorcore.backendv1.entities.enums;

public enum NPCRole {
    MERCHANT("Merchant"),
    QUEST_GIVER("Quest Giver"),
    ENEMY("Enemy"),
    ALLY("Ally"),
    BOSS("Boss");
    private final String displayName;
    NPCRole(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
