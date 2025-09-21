package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.PostNewCharacterRequest;
import com.horrorcore.backendv1.dtos.UpdateCharacterRequest;
import com.horrorcore.backendv1.entities.PlayerCharacter;
import com.horrorcore.backendv1.entities.enums.PlayerClass;
import com.horrorcore.backendv1.repositories.PlayerCharacterRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PlayerCharacterServiceImpl implements PlayerCharacterService {
    private final PlayerCharacterRepository playerCharacterRepository;

    public PlayerCharacterServiceImpl(PlayerCharacterRepository playerCharacterRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
    }


    @Override
    public List<PlayerCharacter> getAllPlayerCharacters() {
        return playerCharacterRepository.findAll();
    }

    @Override
    public PlayerCharacter createPlayerCharacter(PostNewCharacterRequest request) {
        PlayerCharacter newCharacter = new PlayerCharacter();
        newCharacter.setName(request.name());
        newCharacter.setPlayerClass(PlayerClass.valueOf(request.playerClass()));
        newCharacter.setLevel(1);
        newCharacter.setExperience(0);
        switch (PlayerClass.valueOf(request.playerClass())) {
            case WARRIOR -> {
                newCharacter.setHealth(150);
                newCharacter.setMana(50);
                newCharacter.setStrength(15);
                newCharacter.setAgility(10);
                newCharacter.setIntelligence(5);
                newCharacter.setStamina(12);
                newCharacter.setSpirit(8);
            }
            case MAGE -> {
                newCharacter.setHealth(100);
                newCharacter.setMana(150);
                newCharacter.setStrength(5);
                newCharacter.setAgility(8);
                newCharacter.setIntelligence(15);
                newCharacter.setStamina(7);
                newCharacter.setSpirit(12);
            }
            case ROGUE -> {
                newCharacter.setHealth(120);
                newCharacter.setMana(80);
                newCharacter.setStrength(10);
                newCharacter.setAgility(15);
                newCharacter.setIntelligence(8);
                newCharacter.setStamina(10);
                newCharacter.setSpirit(7);
            }
            case CLERIC -> {
                newCharacter.setHealth(110);
                newCharacter.setMana(130);
                newCharacter.setStrength(7);
                newCharacter.setAgility(8);
                newCharacter.setIntelligence(12);
                newCharacter.setStamina(9);
                newCharacter.setSpirit(15);
            }
            case RANGER -> {
                newCharacter.setHealth(130);
                newCharacter.setMana(70);
                newCharacter.setStrength(12);
                newCharacter.setAgility(15);
                newCharacter.setIntelligence(7);
                newCharacter.setStamina(11);
                newCharacter.setSpirit(8);
            }
            case INVENTOR -> {
                newCharacter.setHealth(115);
                newCharacter.setMana(90);
                newCharacter.setStrength(10);
                newCharacter.setAgility(12);
                newCharacter.setIntelligence(14);
                newCharacter.setStamina(9);
                newCharacter.setSpirit(5);
            }
            case ALCHEMIST -> {
                newCharacter.setHealth(105);
                newCharacter.setMana(140);
                newCharacter.setStrength(6);
                newCharacter.setAgility(9);
                newCharacter.setIntelligence(15);
                newCharacter.setStamina(8);
                newCharacter.setSpirit(12);

            }
            case BARD -> {
                newCharacter.setHealth(110);
                newCharacter.setMana(120);
                newCharacter.setStrength(8);
                newCharacter.setAgility(12);
                newCharacter.setIntelligence(10);
                newCharacter.setStamina(9);
                newCharacter.setSpirit(14);
            }
            case INQUISITOR -> {
                newCharacter.setHealth(125);
                newCharacter.setMana(100);
                newCharacter.setStrength(12);
                newCharacter.setAgility(10);
                newCharacter.setIntelligence(12);
                newCharacter.setStamina(11);
                newCharacter.setSpirit(10);
            }
            default -> throw new IllegalArgumentException("Unknown player class: " + request.playerClass());
        }
        return playerCharacterRepository.save(newCharacter);
    }

    @Override
    public PlayerCharacter getPlayerCharacterById(String id) {
        return playerCharacterRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Character not found with id: " + id));
    }

    @Override
    public PlayerCharacter updatePlayerCharacter(String id, UpdateCharacterRequest request) {
        PlayerCharacter existingCharacter = playerCharacterRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Character not found with id: " + id));
        existingCharacter.setName(request.name());
        existingCharacter.setPlayerClass(PlayerClass.valueOf(request.playerClass()));
        existingCharacter.setLevel(request.level());
        existingCharacter.setExperience(request.experiencePoints());
        existingCharacter.setHealth(request.healthPoints());
        existingCharacter.setMana(request.manaPoints());
        existingCharacter.setStrength(request.strength());
        existingCharacter.setAgility(request.agility());
        existingCharacter.setIntelligence(request.intelligence());
        existingCharacter.setStamina(request.stamina());
        existingCharacter.setSpirit(request.spirit());
        playerCharacterRepository.save(existingCharacter);
        return existingCharacter;
    }

    @Override
    public void deletePlayerCharacter(String id) {
        if (!playerCharacterRepository.existsById(id)) {
            throw new IllegalArgumentException("Character not found with id: " + id);
        }
        playerCharacterRepository.deleteById(id);
    }
}
