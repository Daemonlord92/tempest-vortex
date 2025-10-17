package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.Habitat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tempest_vortex_monsters")
public class Monster extends NonPlayerCharacter{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @NotNull(message = "Habitat is required")
    @Enumerated(EnumType.STRING)
    private Habitat habitat;

    @Min(value = 0, message = "Experience points cannot be negative")
    private int experiencePoints;

    @Min(value = 0, message = "Loot drop rate cannot be negative")
    @Max(value = 100, message = "Loot drop rate cannot exceed 100")
    private int lootDropRate; // percentage chance of dropping loot

    @OneToMany(mappedBy = "monster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LootTableEntry> lootTable;
}
