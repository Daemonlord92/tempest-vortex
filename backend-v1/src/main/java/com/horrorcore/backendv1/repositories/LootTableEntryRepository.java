package com.horrorcore.backendv1.repositories;

import com.horrorcore.backendv1.entities.LootTableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LootTableEntryRepository extends JpaRepository<LootTableEntry, String> {

    /**
     * Find all loot entries for a specific monster
     */
    List<LootTableEntry> findByMonsterId(String monsterId);

    /**
     * Find all loot entries for a specific item
     */
    List<LootTableEntry> findByItemId(String itemId);

    /**
     * Find loot entries by drop chance greater than or equal to specified value
     */
    List<LootTableEntry> findByDropChanceGreaterThanEqual(int minDropChance);

    /**
     * Find high-value loot (drop chance above threshold)
     */
    @Query("SELECT l FROM LootTableEntry l WHERE l.dropChance >= :threshold ORDER BY l.dropChance DESC")
    List<LootTableEntry> findRareLoot(@Param("threshold") int threshold);

    /**
     * Find guaranteed drops (100% drop chance)
     */
    @Query("SELECT l FROM LootTableEntry l WHERE l.dropChance = 100")
    List<LootTableEntry> findGuaranteedDrops();

    /**
     * Find loot entries by monster and minimum drop chance
     */
    @Query("SELECT l FROM LootTableEntry l WHERE l.monster.id = :monsterId AND l.dropChance >= :minChance")
    List<LootTableEntry> findByMonsterIdAndMinDropChance(
            @Param("monsterId") String monsterId,
            @Param("minChance") int minChance
    );

    /**
     * Find all monsters that drop a specific item
     */
    @Query("SELECT l FROM LootTableEntry l WHERE l.item.id = :itemId ORDER BY l.dropChance DESC")
    List<LootTableEntry> findMonstersThatDropItem(@Param("itemId") String itemId);

    /**
     * Count loot entries for a monster
     */
    long countByMonsterId(String monsterId);

    /**
     * Delete all loot entries for a monster
     */
    void deleteByMonsterId(String monsterId);

    /**
     * Delete all loot entries for an item
     */
    void deleteByItemId(String itemId);
}


