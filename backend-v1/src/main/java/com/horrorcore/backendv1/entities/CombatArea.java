package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.Habitat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tempest_vortex_combat_areas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CombatArea extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    @NotBlank(message = "Combat area name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Habitat is required")
    private Habitat habitat;

    @Min(value = 1, message = "Level requirement must be at least 1")
    @Max(value = 100, message = "Level requirement cannot exceed 100")
    private int levelRequirement;
}