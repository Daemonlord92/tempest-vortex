package com.horrorcore.backendv1.entities.enums;

public enum Habitat {
    FOREST("Forest"),
    DESERT("Desert"),
    MOUNTAINS("Mountains"),
    SWAMP("Swamp"),
    URBAN("Urban"),
    UNDERWATER("Underwater"),
    CAVE("Cave"),
    PLAINS("Plains"),
    TUNDRA("Tundra"),
    JUNGLE("Jungle");

    private final String displayName;

    Habitat(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
