package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.PlayerInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerInventoryRepository extends JpaRepository<PlayerInventory, String> {

    /**
     * Find all inventory items for a specific player
     */
    List<PlayerInventory> findByPlayerCharacterId(String playerCharacterId);

    /**
     * Find a specific item in a player's inventory
     */
    Optional<PlayerInventory> findByPlayerCharacterIdAndItemId(String playerCharacterId, String itemId);

    /**
     * Find all equipped items for a player
     */
    List<PlayerInventory> findByPlayerCharacterIdAndIsEquippedTrue(String playerCharacterId);

    /**
     * Find all consumable items in a player's inventory
     */
    @Query("SELECT pi FROM PlayerInventory pi WHERE pi.playerCharacter.id = :playerId AND pi.item.isConsumable = true AND pi.quantity > 0")
    List<PlayerInventory> findConsumableItemsByPlayerId(@Param("playerId") String playerId);

    /**
     * Find all equippable items in a player's inventory
     */
    @Query("SELECT pi FROM PlayerInventory pi WHERE pi.playerCharacter.id = :playerId AND pi.item.isEquippable = true")
    List<PlayerInventory> findEquippableItemsByPlayerId(@Param("playerId") String playerId);

    /**
     * Check if a player has a specific item
     */
    boolean existsByPlayerCharacterIdAndItemId(String playerCharacterId, String itemId);
}