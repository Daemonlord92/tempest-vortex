-- Drop table if it exists (from failed migration)
DROP TABLE IF EXISTS tempest_vortex_player_inventory;

-- Create Player Inventory table (junction table with additional fields)
CREATE TABLE tempest_vortex_player_inventory (
    id CHAR(36) PRIMARY KEY,
    player_character_id CHAR(36) NOT NULL,
    item_id CHAR(36) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    is_equipped BOOLEAN NOT NULL DEFAULT FALSE,
    acquired_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (player_character_id) REFERENCES tempest_vortex_player_characters(player_character_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES tempest_vortex_items(item_id) ON DELETE CASCADE,
    CONSTRAINT chk_inventory_quantity CHECK (quantity >= 0),
    CONSTRAINT uq_player_item UNIQUE (player_character_id, item_id)
);

-- Create index for faster lookups
CREATE INDEX idx_player_inventory_player ON tempest_vortex_player_inventory(player_character_id);
CREATE INDEX idx_player_inventory_item ON tempest_vortex_player_inventory(item_id);
CREATE INDEX idx_player_inventory_equipped ON tempest_vortex_player_inventory(is_equipped);

-- Insert sample inventory data for existing players
-- Simple approach: give first 3 players some random items
INSERT INTO tempest_vortex_player_inventory (id, player_character_id, item_id, quantity, is_equipped)
SELECT
    UUID() as id,
    (SELECT player_character_id FROM tempest_vortex_player_characters LIMIT 1 OFFSET 0) as player_character_id,
    item_id,
    CASE WHEN item_is_stackable = 1 THEN 3 ELSE 1 END as quantity,
    FALSE as is_equipped
FROM tempest_vortex_items
LIMIT 3;

INSERT INTO tempest_vortex_player_inventory (id, player_character_id, item_id, quantity, is_equipped)
SELECT
    UUID() as id,
    (SELECT player_character_id FROM tempest_vortex_player_characters LIMIT 1 OFFSET 1) as player_character_id,
    item_id,
    CASE WHEN item_is_stackable = 1 THEN 5 ELSE 1 END as quantity,
    FALSE as is_equipped
FROM tempest_vortex_items
LIMIT 2 OFFSET 3;

INSERT INTO tempest_vortex_player_inventory (id, player_character_id, item_id, quantity, is_equipped)
SELECT
    UUID() as id,
    (SELECT player_character_id FROM tempest_vortex_player_characters LIMIT 1 OFFSET 2) as player_character_id,
    item_id,
    CASE WHEN item_is_stackable = 1 THEN 2 ELSE 1 END as quantity,
    FALSE as is_equipped
FROM tempest_vortex_items
LIMIT 4 OFFSET 5;
