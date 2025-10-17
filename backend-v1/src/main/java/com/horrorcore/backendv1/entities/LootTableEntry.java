package com.horrorcore.backendv1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Monster is required")
    @ManyToOne
    @JoinColumn(name = "monster_id", nullable = false)
    private Monster monster;

    @NotNull(message = "Item is required")
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Min(value = 0, message = "Drop chance cannot be negative")
    @Max(value = 100, message = "Drop chance cannot exceed 100")
    private int dropChance; // percentage chance of this item dropping

    @Min(value = 1, message = "Minimum quantity must be at least 1")
    private int minQuantity;

    @Min(value = 1, message = "Maximum quantity must be at least 1")
    private int maxQuantity;
}