package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.PostNewCharacterRequest;
import com.horrorcore.backendv1.dtos.UpdateCharacterRequest;
import com.horrorcore.backendv1.entities.PlayerCharacter;

import java.util.List;

public interface PlayerCharacterService {
    List<PlayerCharacter> getAllPlayerCharacters();
    PlayerCharacter createPlayerCharacter(PostNewCharacterRequest request);
    PlayerCharacter getPlayerCharacterById(String id);
    PlayerCharacter updatePlayerCharacter(String id, UpdateCharacterRequest request);
    void deletePlayerCharacter(String id);
}
