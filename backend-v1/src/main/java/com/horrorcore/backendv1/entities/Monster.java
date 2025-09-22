package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.Habitat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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
    private Habitat habitat;
    private int experiencePoints;
    private int lootDropRate; // percentage chance of dropping loot

    @OneToMany(mappedBy = "monster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LootTableEntry> lootTable;
}
