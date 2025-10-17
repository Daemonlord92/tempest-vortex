-- Create Towns table
CREATE TABLE tempest_vortex_towns (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create Locations table
CREATE TABLE tempest_vortex_locations (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Junction table for Location biomes (since it's a list)
CREATE TABLE tempest_vortex_location_biomes (
    id CHAR(36) PRIMARY KEY,
    location_id CHAR(36) NOT NULL,
    biome ENUM('FOREST','DESERT','MOUNTAINS','SWAMP','URBAN','UNDERWATER','CAVE','PLAINS','TUNDRA','JUNGLE') NOT NULL,
    FOREIGN KEY (location_id) REFERENCES tempest_vortex_locations(id) ON DELETE CASCADE
);

-- Junction table for Location-Town relationship
CREATE TABLE tempest_vortex_location_towns (
    location_id CHAR(36) NOT NULL,
    town_id CHAR(36) NOT NULL,
    PRIMARY KEY (location_id, town_id),
    FOREIGN KEY (location_id) REFERENCES tempest_vortex_locations(id) ON DELETE CASCADE,
    FOREIGN KEY (town_id) REFERENCES tempest_vortex_towns(id) ON DELETE CASCADE
);

-- Junction table for Location-CombatArea relationship
CREATE TABLE tempest_vortex_location_combat_areas (
    location_id CHAR(36) NOT NULL,
    combat_area_id CHAR(36) NOT NULL,
    PRIMARY KEY (location_id, combat_area_id),
    FOREIGN KEY (location_id) REFERENCES tempest_vortex_locations(id) ON DELETE CASCADE,
    FOREIGN KEY (combat_area_id) REFERENCES tempest_vortex_combat_areas(id) ON DELETE CASCADE
);

-- Insert sample towns
INSERT INTO tempest_vortex_towns (id, name, description)
VALUES
(UUID(), 'Silverbrook', 'A peaceful town nestled by a river'),
(UUID(), 'Ironhold', 'A fortified city known for its blacksmiths'),
(UUID(), 'Mystic Vale', 'A town where magic users gather'),
(UUID(), 'Thornhaven', 'A rough frontier settlement'),
(UUID(), 'Crystalport', 'A coastal trading hub');

-- Insert sample locations
INSERT INTO tempest_vortex_locations (id, name, description)
VALUES
(UUID(), 'Northern Territories', 'Cold and unforgiving lands'),
(UUID(), 'Eastern Kingdoms', 'Prosperous lands with many towns'),
(UUID(), 'Western Wilderness', 'Untamed frontier'),
(UUID(), 'Southern Archipelago', 'Island chain with hidden treasures');

