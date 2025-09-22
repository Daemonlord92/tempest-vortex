package com.horrorcore.backendv1.entities;

import com.horrorcore.backendv1.entities.enums.NPCRole;
import jakarta.persistence.MappedSuperclass;
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
    private String name;
    private NPCRole role;
    private int level;
    private String description;
    private int healthPoints;
    private int attackPower;
    private int defense;
}
