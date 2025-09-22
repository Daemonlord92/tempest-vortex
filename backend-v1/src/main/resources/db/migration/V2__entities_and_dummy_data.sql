-- Create Item table
CREATE TABLE tempest_vortex_items (
    item_id CHAR(36) PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    item_type ENUM('WEAPON','ARMOR','CONSUMABLE','MATERIAL','QUEST','MISC') NOT NULL,
    item_level_requirement INT NOT NULL,
    item_is_equippable BOOLEAN NOT NULL,
    item_is_consumable BOOLEAN NOT NULL,
    item_is_unique BOOLEAN NOT NULL,
    item_is_tradable BOOLEAN NOT NULL,
    item_is_stackable BOOLEAN NOT NULL,
    item_max_stack_size INT,
    item_value INT NOT NULL,
    item_description VARCHAR(1000),
    rarity ENUM('COMMON','UNCOMMON','RARE','EPIC','LEGENDARY','MYTHIC')
);

-- Create Monster table
CREATE TABLE tempest_vortex_monsters (
    monster_id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role ENUM('MERCHANT','QUEST_GIVER','ENEMY','ALLY','BOSS') NOT NULL,
    level INT NOT NULL,
    description VARCHAR(1000),
    health_points INT NOT NULL,
    attack_power INT NOT NULL,
    defense INT NOT NULL,
    habitat ENUM('FOREST','CAVE','DUNGEON','PLAINS','MOUNTAIN','SWAMP','DESERT','CITY','VILLAGE','RUINS') NOT NULL,
    experience_points INT NOT NULL,
    loot_drop_rate INT NOT NULL
);

-- Create LootTableEntry table
CREATE TABLE tempest_vortex_loot_table_entries (
    loot_table_entry_id CHAR(36) PRIMARY KEY,
    monster_id CHAR(36) NOT NULL,
    item_id CHAR(36) NOT NULL,
    drop_chance INT NOT NULL,
    min_quantity INT NOT NULL,
    max_quantity INT NOT NULL,
    FOREIGN KEY (monster_id) REFERENCES tempest_vortex_monsters(monster_id),
    FOREIGN KEY (item_id) REFERENCES tempest_vortex_items(item_id)
);

-- Insert dummy items
INSERT INTO tempest_vortex_items (item_id, item_name, item_type, item_level_requirement, item_is_equippable, item_is_consumable, item_is_unique, item_is_tradable, item_is_stackable, item_max_stack_size, item_value, item_description, rarity)
VALUES
(UUID(), 'Iron Sword', 'WEAPON', 1, TRUE, FALSE, FALSE, TRUE, FALSE, NULL, 100, 'A basic iron sword.', 'COMMON'),
(UUID(), 'Healing Potion', 'CONSUMABLE', 1, FALSE, TRUE, FALSE, TRUE, TRUE, 10, 50, 'Restores health.', 'COMMON'),
(UUID(), 'Leather Armor', 'ARMOR', 2, TRUE, FALSE, FALSE, TRUE, FALSE, NULL, 120, 'Simple leather armor.', 'UNCOMMON'),
(UUID(), 'Magic Scroll', 'CONSUMABLE', 5, FALSE, TRUE, TRUE, FALSE, FALSE, NULL, 300, 'Casts a random spell.', 'RARE'),
(UUID(), 'Gold Coin', 'MISC', 1, FALSE, FALSE, FALSE, TRUE, TRUE, 100, 1, 'Currency.', 'COMMON'),
(UUID(), 'Dragon Scale', 'MATERIAL', 10, FALSE, FALSE, TRUE, FALSE, TRUE, 5, 500, 'Rare crafting material.', 'EPIC'),
(UUID(), 'Quest Amulet', 'QUEST', 1, TRUE, FALSE, TRUE, FALSE, FALSE, NULL, 0, 'Required for a quest.', 'RARE'),
(UUID(), 'Elixir of Mana', 'CONSUMABLE', 3, FALSE, TRUE, FALSE, TRUE, TRUE, 5, 80, 'Restores mana.', 'UNCOMMON'),
(UUID(), 'Steel Shield', 'ARMOR', 4, TRUE, FALSE, FALSE, TRUE, FALSE, NULL, 200, 'Protects against attacks.', 'UNCOMMON'),
(UUID(), 'Mystic Gem', 'MISC', 7, FALSE, FALSE, TRUE, FALSE, FALSE, NULL, 1000, 'A mysterious gem.', 'LEGENDARY'),
(UUID(), 'Bandit Mask', 'MISC', 2, TRUE, FALSE, FALSE, TRUE, FALSE, NULL, 60, 'Worn by bandits.', 'COMMON'),
(UUID(), 'Herb Bundle', 'MATERIAL', 1, FALSE, FALSE, FALSE, TRUE, TRUE, 20, 15, 'Used for potions.', 'COMMON'),
(UUID(), 'Ancient Tome', 'QUEST', 8, FALSE, FALSE, TRUE, FALSE, FALSE, NULL, 0, 'Contains lost knowledge.', 'EPIC'),
(UUID(), 'Silver Arrow', 'MATERIAL', 3, FALSE, FALSE, FALSE, TRUE, TRUE, 50, 5, 'Effective against monsters.', 'UNCOMMON'),
(UUID(), 'Phoenix Feather', 'MATERIAL', 12, FALSE, FALSE, TRUE, FALSE, TRUE, 1, 2000, 'Revives the fallen.', 'MYTHIC');

-- Insert dummy monsters
INSERT INTO tempest_vortex_monsters (monster_id, name, role, level, description, health_points, attack_power, defense, habitat, experience_points, loot_drop_rate)
VALUES
(UUID(), 'Goblin', 'ENEMY', 2, 'A sneaky goblin.', 30, 5, 2, 'FOREST', 20, 50),
(UUID(), 'Orc Warrior', 'ENEMY', 5, 'Strong and aggressive.', 80, 15, 8, 'PLAINS', 60, 40),
(UUID(), 'Forest Wolf', 'ENEMY', 3, 'Fast and wild.', 40, 8, 3, 'FOREST', 30, 35),
(UUID(), 'Bandit Leader', 'BOSS', 7, 'Leads the bandits.', 120, 20, 10, 'RUINS', 150, 60),
(UUID(), 'Cave Bat', 'ENEMY', 1, 'Small and annoying.', 15, 3, 1, 'CAVE', 10, 25),
(UUID(), 'Swamp Hag', 'ENEMY', 6, 'Uses poison.', 70, 12, 5, 'SWAMP', 80, 45),
(UUID(), 'Mountain Troll', 'ENEMY', 10, 'Huge and tough.', 200, 25, 20, 'MOUNTAIN', 300, 70),
(UUID(), 'Desert Scorpion', 'ENEMY', 4, 'Venomous sting.', 50, 10, 4, 'DESERT', 40, 30),
(UUID(), 'Dungeon Skeleton', 'ENEMY', 5, 'Undead warrior.', 60, 13, 6, 'DUNGEON', 55, 38),
(UUID(), 'City Thief', 'ENEMY', 3, 'Steals valuables.', 35, 7, 2, 'CITY', 25, 20),
(UUID(), 'Village Elder', 'QUEST_GIVER', 1, 'Gives quests.', 20, 0, 1, 'VILLAGE', 0, 0),
(UUID(), 'Merchant', 'MERCHANT', 1, 'Sells items.', 25, 0, 2, 'CITY', 0, 0),
(UUID(), 'Ally Knight', 'ALLY', 8, 'Fights with you.', 150, 18, 15, 'PLAINS', 0, 0),
(UUID(), 'Ruins Guardian', 'BOSS', 12, 'Protects the ruins.', 250, 30, 25, 'RUINS', 500, 80),
(UUID(), 'Ancient Dragon', 'BOSS', 15, 'Legendary beast.', 1000, 50, 40, 'MOUNTAIN', 2000, 100);

-- Insert dummy loot table entries (randomized associations)
INSERT INTO tempest_vortex_loot_table_entries (loot_table_entry_id, monster_id, item_id, drop_chance, min_quantity, max_quantity)
SELECT UUID(), m.monster_id, i.item_id, FLOOR(RAND()*100), 1, FLOOR(RAND()*5)+1
FROM (SELECT monster_id FROM tempest_vortex_monsters LIMIT 15) m
JOIN (SELECT item_id FROM tempest_vortex_items LIMIT 15) i
LIMIT 15;