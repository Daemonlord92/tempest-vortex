-- Create Shopkeeper table (extends NonPlayerCharacter)
CREATE TABLE tempest_vortex_shopkeepers (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role ENUM('MERCHANT','QUEST_GIVER','ENEMY','ALLY','BOSS') NOT NULL,
    level INT NOT NULL,
    description VARCHAR(1000),
    health_points INT NOT NULL,
    attack_power INT NOT NULL,
    defense INT NOT NULL,
    town_id CHAR(36),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_shopkeeper_level CHECK (level >= 1 AND level <= 100),
    CONSTRAINT chk_shopkeeper_health CHECK (health_points >= 0),
    CONSTRAINT chk_shopkeeper_attack CHECK (attack_power >= 0),
    CONSTRAINT chk_shopkeeper_defense CHECK (defense >= 0),
    FOREIGN KEY (town_id) REFERENCES tempest_vortex_towns(id) ON DELETE SET NULL
);

-- Junction table for Shopkeeper inventory (many-to-many with items)
CREATE TABLE tempest_vortex_shopkeeper_inventory (
    shopkeeper_id CHAR(36) NOT NULL,
    item_id CHAR(36) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 1,
    price_modifier DECIMAL(5,2) NOT NULL DEFAULT 1.00,
    PRIMARY KEY (shopkeeper_id, item_id),
    FOREIGN KEY (shopkeeper_id) REFERENCES tempest_vortex_shopkeepers(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES tempest_vortex_items(item_id) ON DELETE CASCADE,
    CONSTRAINT chk_stock_quantity CHECK (stock_quantity >= 0),
    CONSTRAINT chk_price_modifier CHECK (price_modifier >= 0)
);

-- Insert sample shopkeepers with NPC fields
INSERT INTO tempest_vortex_shopkeepers (id, name, role, level, description, health_points, attack_power, defense)
VALUES
(UUID(), 'Greta the Merchant', 'MERCHANT', 5, 'A friendly shopkeeper with a wide selection', 50, 2, 5),
(UUID(), 'Balthazar the Mystic', 'MERCHANT', 10, 'Sells magical items and potions', 60, 5, 8),
(UUID(), 'Ironbeard the Blacksmith', 'MERCHANT', 8, 'Expert weaponsmith and armorer', 80, 10, 15),
(UUID(), 'Willow the Herbalist', 'MERCHANT', 6, 'Specializes in herbs and healing supplies', 45, 1, 3),
(UUID(), 'Shady Pete', 'MERCHANT', 7, 'Deals in rare and questionable items', 55, 4, 6);
