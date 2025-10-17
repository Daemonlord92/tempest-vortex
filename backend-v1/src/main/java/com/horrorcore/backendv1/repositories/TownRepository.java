package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, String> {

    /**
     * Find town by name (case-insensitive)
     */
    Optional<Town> findByNameIgnoreCase(String name);

    /**
     * Find towns by name containing (case-insensitive)
     */
    List<Town> findByNameContainingIgnoreCase(String name);

    /**
     * Find all towns that have shopkeepers
     */
    @Query("SELECT DISTINCT t FROM Town t JOIN t.shopkeepers s WHERE SIZE(t.shopkeepers) > 0")
    List<Town> findTownsWithShopkeepers();

    /**
     * Check if a town exists by name
     */
    boolean existsByNameIgnoreCase(String name);
}