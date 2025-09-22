package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, String> {
}
