package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.PlayerClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tempest_vortex_player_characters")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerCharacter extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "player_character_id", updatable = false, nullable = false)
    private String id;

    @Column(name = "player_character_name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
    private String name;

    @NotNull(message = "Player class is required")
    @Column(name = "player_character_class", nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerClass playerClass;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 100, message = "Level cannot exceed 100")
    @Column(name = "player_character_level", nullable = false)
    private int level = 1;

    @Min(value = 0, message = "Experience cannot be negative")
    @Column(name = "player_character_experience", nullable = false)
    private int experience = 0;

    @Min(value = 1, message = "Health must be at least 1")
    @Column(name = "player_character_health", nullable = false)
    private int health;

    @Min(value = 0, message = "Mana cannot be negative")
    @Column(name = "player_character_mana", nullable = false)
    private int mana;

    @Min(value = 1, message = "Strength must be at least 1")
    @Column(name = "player_character_strength", nullable = false)
    private int strength;

    @Min(value = 1, message = "Agility must be at least 1")
    @Column(name = "player_character_agility", nullable = false)
    private int agility;

    @Min(value = 1, message = "Intelligence must be at least 1")
    @Column(name = "player_character_intelligence", nullable = false)
    private int intelligence;

    @Min(value = 1, message = "Stamina must be at least 1")
    @Column(name = "player_character_stamina", nullable = false)
    private int stamina;

    @Min(value = 1, message = "Spirit must be at least 1")
    @Column(name = "player_character_spirit", nullable = false)
    private int spirit;
}
