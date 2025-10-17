package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateSpellRequest;
import com.horrorcore.backendv1.dtos.UpdateSpellRequest;
import com.horrorcore.backendv1.entities.Spell;

import java.util.List;

public interface SpellService {

    /**
     * Get all spells
     */
    List<Spell> getAllSpells();

    /**
     * Get spell by ID
     */
    Spell getSpellById(String id);

    /**
     * Create a new spell
     */
    Spell createSpell(CreateSpellRequest request);

    /**
     * Update an existing spell
     */
    Spell updateSpell(String id, UpdateSpellRequest request);

    /**
     * Delete a spell
     */
    void deleteSpell(String id);

    /**
     * Get spells available for a specific player level
     */
    List<Spell> getSpellsByPlayerLevel(int level);

    /**
     * Search spells by name
     */
    List<Spell> searchSpellsByName(String name);
}

