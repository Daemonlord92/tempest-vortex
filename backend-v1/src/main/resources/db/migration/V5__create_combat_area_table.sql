-- Create CombatArea table
CREATE TABLE tempest_vortex_combat_areas (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    habitat ENUM('FOREST','DESERT','MOUNTAINS','SWAMP','URBAN','UNDERWATER','CAVE','PLAINS','TUNDRA','JUNGLE') NOT NULL,
    level_requirement INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_combat_area_level CHECK (level_requirement >= 1 AND level_requirement <= 100)
);

-- Insert sample combat areas
INSERT INTO tempest_vortex_combat_areas (id, name, description, habitat, level_requirement)
VALUES
(UUID(), 'Dark Forest Glade', 'A mysterious clearing in the dark forest', 'FOREST', 1),
(UUID(), 'Scorched Dunes', 'Harsh desert wasteland', 'DESERT', 5),
(UUID(), 'Mountain Peak', 'Treacherous mountain heights', 'MOUNTAINS', 10),
(UUID(), 'Murky Swamp', 'A dangerous swamp filled with creatures', 'SWAMP', 6),
(UUID(), 'Abandoned City', 'Ruins of an ancient city', 'URBAN', 8),
(UUID(), 'Crystal Cavern', 'A cave filled with magical crystals', 'CAVE', 7),
(UUID(), 'Frozen Tundra', 'Ice-covered wasteland', 'TUNDRA', 12),
(UUID(), 'Wild Jungle', 'Dense jungle with hidden dangers', 'JUNGLE', 9),
(UUID(), 'Plains Battlefield', 'Open field scarred by battles', 'PLAINS', 4),
(UUID(), 'Underwater Grotto', 'Submerged cavern system', 'UNDERWATER', 15);

