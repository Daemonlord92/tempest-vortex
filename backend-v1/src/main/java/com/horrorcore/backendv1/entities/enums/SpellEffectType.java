package com.horrorcore.backendv1.entities.enums;

public enum SpellEffectType {
    FIRE("Fire"),
    ICE("Ice"),
    LIGHTNING("Lightning"),
    POISON("Poison"),
    HEALING("Healing"),
    SHIELD("Shield"),
    SUMMONING("Summoning"),
    CURSE("Curse"),
    BUFF("Buff"),
    DEBUFF("Debuff"),
    VOID("Void"),
    HOLY("Holy"),
    DARK("Dark"),
    ARCANE("Arcane"),
    NATURE("Nature"),
    WATER("Water"),
    WIND("Wind"),
    EARTH("Earth"),
    TIME("Time"),
    SPACE("Space"),
    MIND("Mind"),
    VAMPIRIC("Vampiric"),
    CHAOS("Chaos");
    private final String displayName;
    SpellEffectType(String displayName) {
        this.displayName = displayName;
    }
}
