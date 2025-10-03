package com.horrorcore.backendv1.dtos;

import com.horrorcore.backendv1.dtos.enums.CombatStatus;
import com.horrorcore.backendv1.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Queue;

@Data
@AllArgsConstructor
public class CombatInformation {
    private final PlayerCharacter playerCharacter;
    private final List<Monster> monsters;
    private Queue<AuditableEntity> turnOrder;
    private CombatStatus combatStatus;
    private int roundNumber;
    private Spell selectedSpell;
    private Item selectedItem;
    private Monster targetMonster;
}
