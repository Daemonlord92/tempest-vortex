package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Shopkeeper;
import com.horrorcore.backendv1.entities.enums.NPCRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, String> {

    /**
     * Find shopkeeper by name (case-insensitive)
     */
    Optional<Shopkeeper> findByNameIgnoreCase(String name);

    /**
     * Find shopkeepers by name containing (case-insensitive)
     */
    List<Shopkeeper> findByNameContainingIgnoreCase(String name);

    /**
     * Find all shopkeepers in a specific town
     */
    List<Shopkeeper> findByTownId(String townId);

    /**
     * Find shopkeepers by role
     */
    List<Shopkeeper> findByRole(NPCRole role);

    /**
     * Find shopkeepers in a town with a specific role
     */
    List<Shopkeeper> findByTownIdAndRole(String townId, NPCRole role);

    /**
     * Find shopkeepers that sell a specific item
     */
    @Query("SELECT s FROM Shopkeeper s JOIN s.inventory i WHERE i.id = :itemId")
    List<Shopkeeper> findByItemId(@Param("itemId") String itemId);

    /**
     * Find shopkeepers with inventory size greater than specified amount
     */
    @Query("SELECT s FROM Shopkeeper s WHERE SIZE(s.inventory) > :minItems")
    List<Shopkeeper> findShopkeepersWithMinimumInventory(@Param("minItems") int minItems);

    /**
     * Count shopkeepers in a town
     */
    long countByTownId(String townId);
}

