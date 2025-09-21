package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.PlayerCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, String> {
}
