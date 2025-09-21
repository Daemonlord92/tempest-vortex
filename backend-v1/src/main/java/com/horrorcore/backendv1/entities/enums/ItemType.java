package com.horrorcore.backendv1.entities.enums;

public enum ItemType {
    WEAPON("Weapon"),
    ARMOR("Armor"),
    CONSUMABLE("Consumable"),
    MATERIAL("Material"),
    QUEST("Quest Item"),
    MISC("Miscellaneous");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
