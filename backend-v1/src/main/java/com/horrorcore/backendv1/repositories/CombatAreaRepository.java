package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.CombatArea;
import com.horrorcore.backendv1.entities.enums.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CombatAreaRepository extends JpaRepository<CombatArea, String> {

    /**
     * Find combat area by name (case-insensitive)
     */
    Optional<CombatArea> findByNameIgnoreCase(String name);

    /**
     * Find combat areas by name containing (case-insensitive)
     */
    List<CombatArea> findByNameContainingIgnoreCase(String name);

    /**
     * Find combat areas by habitat
     */
    List<CombatArea> findByHabitat(Habitat habitat);

    /**
     * Find combat areas by level requirement range
     */
    List<CombatArea> findByLevelRequirementBetween(int minLevel, int maxLevel);

    /**
     * Find combat areas suitable for a player level (at or below level requirement)
     */
    List<CombatArea> findByLevelRequirementLessThanEqual(int playerLevel);

    /**
     * Find combat areas by exact level requirement
     */
    List<CombatArea> findByLevelRequirement(int level);

    /**
     * Find all combat areas ordered by level requirement
     */
    @Query("SELECT ca FROM CombatArea ca ORDER BY ca.levelRequirement ASC")
    List<CombatArea> findAllOrderedByLevel();
}

