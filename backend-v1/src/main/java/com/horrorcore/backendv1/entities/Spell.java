package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.SpellEffectType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tempest_vortex_spells")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spell extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Spell name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Min(value = 0, message = "Mana cost cannot be negative")
    private int manaCost;

    @Min(value = 0, message = "Cooldown cannot be negative")
    private int cooldown; // in rounds

    @Min(value = 1, message = "Level requirement must be at least 1")
    @Max(value = 100, message = "Level requirement cannot exceed 100")
    private int levelRequirement;

    @Min(value = 0, message = "Positive effect cannot be negative")
    private int positiveEffect; // e.g., healing amount

    @Min(value = 0, message = "Negative effect cannot be negative")
    private int negativeEffect; // e.g., damage amount

    @NotNull(message = "Effect type is required")
    @Enumerated(EnumType.STRING)
    private SpellEffectType effectType;
}
