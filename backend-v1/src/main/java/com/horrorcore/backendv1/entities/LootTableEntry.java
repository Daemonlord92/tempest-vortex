package com.horrorcore.backendv1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tempest_vortex_loot_table_entries")
public class LootTableEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "monster_id", nullable = false)
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int dropChance; // percentage chance of this item dropping
    private int minQuantity;
    private int maxQuantity;
}