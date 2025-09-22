package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
