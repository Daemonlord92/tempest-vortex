package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.Habitat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tempest_vortex_locations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Location extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Location name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @ElementCollection(targetClass = Habitat.class)
    @CollectionTable(name = "tempest_vortex_location_biomes", joinColumns = @JoinColumn(name = "location_id"))
    @Column(name = "biome")
    @Enumerated(EnumType.STRING)
    private List<Habitat> biome;

    @ManyToMany
    @JoinTable(
        name = "tempest_vortex_location_towns",
        joinColumns = @JoinColumn(name = "location_id"),
        inverseJoinColumns = @JoinColumn(name = "town_id")
    )
    private List<Town> towns;

    @ManyToMany
    @JoinTable(
        name = "tempest_vortex_location_combat_areas",
        joinColumns = @JoinColumn(name = "location_id"),
        inverseJoinColumns = @JoinColumn(name = "combat_area_id")
    )
    private List<CombatArea> combatAreas;
}
