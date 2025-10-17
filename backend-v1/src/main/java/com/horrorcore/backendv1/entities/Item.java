package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.ItemRarity;
import com.horrorcore.backendv1.entities.enums.ItemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Item name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @Column(name = "item_name", nullable = false)
    private String name;

    @NotNull(message = "Item type is required")
    @Column(name = "item_type", nullable = false)
    private ItemType type;

    @Min(value = 1, message = "Level requirement must be at least 1")
    @Max(value = 100, message = "Level requirement cannot exceed 100")
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

    @Min(value = 1, message = "Max stack size must be at least 1")
    @Column(name = "item_max_stack_size")
    private int maxStackSize;

    @Min(value = 0, message = "Item value cannot be negative")
    @Column(name = "item_value", nullable = false)
    private int value; // in-game currency value

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "item_description", length = 1000)
    private String description;

    @NotNull(message = "Item rarity is required")
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;
}
