package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CreateSpellRequest;
import com.horrorcore.backendv1.dtos.UpdateSpellRequest;
import com.horrorcore.backendv1.entities.Spell;
import com.horrorcore.backendv1.entities.enums.SpellEffectType;
import com.horrorcore.backendv1.exceptions.ResourceNotFoundException;
import com.horrorcore.backendv1.repositories.SpellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpellServiceImpl implements SpellService {

    private final SpellRepository spellRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Spell> getAllSpells() {
        log.info("Fetching all spells");
        return spellRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Spell getSpellById(String id) {
        log.info("Fetching spell with id: {}", id);
        return spellRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spell", "id", id));
    }

    @Override
    @Transactional
    public Spell createSpell(CreateSpellRequest request) {
        log.info("Creating new spell: {}", request.name());

        Spell spell = new Spell();
        spell.setName(request.name());
        spell.setDescription(request.description());
        spell.setManaCost(request.manaCost());
        spell.setCooldown(request.cooldown());
        spell.setLevelRequirement(request.levelRequirement());
        spell.setPositiveEffect(request.positiveEffect());
        spell.setNegativeEffect(request.negativeEffect());
        spell.setEffectType(SpellEffectType.valueOf(request.effectType()));

        Spell savedSpell = spellRepository.save(spell);
        log.info("Created spell with id: {}", savedSpell.getId());

        return savedSpell;
    }

    @Override
    @Transactional
    public Spell updateSpell(String id, UpdateSpellRequest request) {
        log.info("Updating spell with id: {}", id);

        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spell", "id", id));

        spell.setName(request.name());
        spell.setDescription(request.description());
        spell.setManaCost(request.manaCost());
        spell.setCooldown(request.cooldown());
        spell.setLevelRequirement(request.levelRequirement());
        spell.setPositiveEffect(request.positiveEffect());
        spell.setNegativeEffect(request.negativeEffect());
        spell.setEffectType(SpellEffectType.valueOf(request.effectType()));

        Spell updatedSpell = spellRepository.save(spell);
        log.info("Updated spell with id: {}", updatedSpell.getId());

        return updatedSpell;
    }

    @Override
    @Transactional
    public void deleteSpell(String id) {
        log.info("Deleting spell with id: {}", id);

        if (!spellRepository.existsById(id)) {
            throw new ResourceNotFoundException("Spell", "id", id);
        }

        spellRepository.deleteById(id);
        log.info("Deleted spell with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spell> getSpellsByPlayerLevel(int level) {
        log.info("Fetching spells for player level: {}", level);
        return spellRepository.findByLevelRequirementLessThanEqual(level);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spell> searchSpellsByName(String name) {
        log.info("Searching spells by name: {}", name);
        return spellRepository.findByNameContainingIgnoreCase(name);
    }
}

