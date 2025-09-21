package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tempest_vortex_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_id", updatable = false, nullable = false)
    private String id;
    @Column(name = "item_name", nullable = false)
    private String name;
    @Column(name = "item_type", nullable = false)
    private ItemType type;
    @Column(name = "item_level_requirement", nullable = false)
    private int levelRequirement;
    @Column(name = "item_is_equippable", nullable = false)
    private boolean isEquippable;
    @Column(name = "item_is_consumable", nullable = false)
    private boolean isConsumable;
    @Column(name = "item_is_unique", nullable = false)
    private boolean isUnique;
    @Column(name = "item_is_tradable", nullable = false)
    private boolean isTradable;
    @Column(name = "item_is_stackable", nullable = false)
    private boolean isStackable;
    @Column(name = "item_max_stack_size")
    private int maxStackSize;
    @Column(name = "item_value", nullable = false)
    private int value; // in-game currency value
    @Column(name = "item_description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
}
