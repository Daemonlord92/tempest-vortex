package com.horrorcore.backendv1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tempest_vortex_towns")
@EqualsAndHashCode(callSuper = true)
public class Town extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Town name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    // Separate relationships for each concrete NPC type
    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shopkeeper> shopkeepers = new ArrayList<>();

    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Monster> guards = new ArrayList<>();

    // Helper method to get all NPCs regardless of type
    @Transient
    public List<NonPlayerCharacter> getAllNpcs() {
        List<NonPlayerCharacter> allNpcs = new ArrayList<>();
        if (shopkeepers != null) {
            allNpcs.addAll(shopkeepers);
        }
        if (guards != null) {
            allNpcs.addAll(guards);
        }
        return allNpcs;
    }
}