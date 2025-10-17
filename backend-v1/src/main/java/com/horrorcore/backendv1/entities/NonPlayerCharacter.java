package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.NPCRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class NonPlayerCharacter extends AuditableEntity{
    @NotBlank(message = "NPC name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "NPC role is required")
    @Enumerated(EnumType.STRING)
    private NPCRole role;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 100, message = "Level cannot exceed 100")
    private int level;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Min(value = 0, message = "Health points cannot be negative")
    private int healthPoints;

    @Min(value = 0, message = "Attack power cannot be negative")
    private int attackPower;

    @Min(value = 0, message = "Defense cannot be negative")
    private int defense;
}
