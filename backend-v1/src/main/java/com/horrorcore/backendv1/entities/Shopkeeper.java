package com.horrorcore.backendv1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tempest_vortex_shopkeepers")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Shopkeeper extends NonPlayerCharacter{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @ManyToMany
    @JoinTable(
        name = "tempest_vortex_shopkeeper_inventory",
        joinColumns = @JoinColumn(name = "shopkeeper_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> inventory;
}
