package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, String> {

    /**
     * Find spells by minimum level requirement
     */
    List<Spell> findByLevelRequirementLessThanEqual(int level);

    /**
     * Find spells by name (case-insensitive)
     */
    List<Spell> findByNameContainingIgnoreCase(String name);
}

