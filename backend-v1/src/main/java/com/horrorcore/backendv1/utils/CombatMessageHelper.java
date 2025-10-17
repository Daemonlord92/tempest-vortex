package com.horrorcore.backendv1.utils;

import com.horrorcore.backendv1.dtos.CombatInformation;
import com.horrorcore.backendv1.dtos.enums.CombatStatus;

public class CombatMessageHelper {

    private CombatMessageHelper() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String buildActionMessage(CombatStatus action, CombatInformation combat) {
        return switch (action) {
            case ATTACK -> "Attack executed!";
            case DEFEND -> "Defensive stance taken!";
            case CAST_SPELL -> combat.getSelectedSpell() != null
                    ? "Spell " + combat.getSelectedSpell().getName() + " cast!"
                    : "Spell cast!";
            case USE_ITEM -> combat.getSelectedItem() != null
                    ? "Used " + combat.getSelectedItem().getName() + "!"
                    : "Item used!";
            case FLEE -> combat.getMonsters().isEmpty()
                    ? "Successfully fled from combat!"
                    : "Failed to flee!";
        };
    }

    public static String buildCombatStartMessage(String playerName, int monsterCount) {
        return String.format("%s enters combat against %d monster(s)!", playerName, monsterCount);
    }

    public static String buildCombatEndMessage(boolean playerWon, int roundsCompleted) {
        if (playerWon) {
            return String.format("Victory! Combat ended after %d rounds.", roundsCompleted);
        } else {
            return String.format("Defeat! You were defeated after %d rounds.", roundsCompleted);
        }
    }

    public static String buildDamageMessage(String attackerName, String targetName, int damage) {
        return String.format("%s deals %d damage to %s!", attackerName, targetName, damage);
    }

    public static String buildHealingMessage(String characterName, int healing) {
        return String.format("%s recovers %d health!", characterName, healing);
    }
}

