package com.horrorcore.backendv1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tempest_vortex_player_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotNull(message = "Player character is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_character_id", nullable = false)
    private PlayerCharacter playerCharacter;

    @NotNull(message = "Item is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Builder.Default
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(name = "quantity", nullable = false)
    private int quantity = 1;

    @Builder.Default
    @Column(name = "is_equipped", nullable = false)
    private boolean isEquipped = false;

    @Builder.Default
    @Column(name = "acquired_at", nullable = false)
    private LocalDateTime acquiredAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.acquiredAt == null) {
            this.acquiredAt = LocalDateTime.now();
        }
    }

    // Business logic methods

    public void increaseQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.quantity < amount) {
            throw new IllegalArgumentException("Not enough items in inventory");
        }
        this.quantity -= amount;
    }

    public boolean canBeEquipped() {
        return this.item != null && this.item.isEquippable();
    }

    public boolean canBeConsumed() {
        return this.item != null && this.item.isConsumable() && this.quantity > 0;
    }

    public void equip() {
        if (!canBeEquipped()) {
            throw new IllegalStateException("This item cannot be equipped");
        }
        this.isEquipped = true;
    }

    public void unequip() {
        this.isEquipped = false;
    }

    public void consume() {
        if (!canBeConsumed()) {
            throw new IllegalStateException("This item cannot be consumed or quantity is 0");
        }
        decreaseQuantity(1);
    }
}
