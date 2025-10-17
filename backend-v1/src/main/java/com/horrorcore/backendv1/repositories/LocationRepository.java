package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Location;
import com.horrorcore.backendv1.entities.enums.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    /**
     * Find location by name (case-insensitive)
     */
    Optional<Location> findByNameIgnoreCase(String name);

    /**
     * Find locations by name containing (case-insensitive)
     */
    List<Location> findByNameContainingIgnoreCase(String name);

    /**
     * Find locations by biome/habitat
     */
    @Query("SELECT l FROM Location l JOIN l.biome b WHERE b = :habitat")
    List<Location> findByBiome(@Param("habitat") Habitat habitat);

    /**
     * Find locations that contain a specific town
     */
    @Query("SELECT l FROM Location l JOIN l.towns t WHERE t.id = :townId")
    List<Location> findByTownId(@Param("townId") String townId);

    /**
     * Find locations that have combat areas
     */
    @Query("SELECT DISTINCT l FROM Location l WHERE SIZE(l.combatAreas) > 0")
    List<Location> findLocationsWithCombatAreas();
}

