package com.horrorcore.backendv1.entities.enums;

public enum PlayerClass {
    WARRIOR("Warrior"),
    MAGE("Mage"),
    ROGUE("Rogue"),
    CLERIC("Cleric"),
    RANGER("Ranger"),
    INVENTOR("Inventor"),
    ALCHEMIST("Alchemist"),
    BARD("Bard"),
    INQUISITOR("Inquisitor");

    private final String displayName;

    PlayerClass(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
