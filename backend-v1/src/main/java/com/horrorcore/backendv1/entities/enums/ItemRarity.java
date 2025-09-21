package com.horrorcore.backendv1.entities.enums;

public enum ItemRarity {
    JUNK("Junk"),
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary"),
    MYTHIC("Mythic");
    private final String displayName;
    ItemRarity(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
