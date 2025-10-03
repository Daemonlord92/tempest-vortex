package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.SpellEffectType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spell extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private int manaCost;
    private int cooldown; // in rounds
    private int levelRequirement;
    private int positiveEffect; // e.g., healing amount
    private int negativeEffect; // e.g., damage amount
    private SpellEffectType effectType;
}
