CREATE TABLE tempest_vortex_player_characters (
    player_character_id CHAR(36) PRIMARY KEY,
    player_character_name VARCHAR(255) NOT NULL,
    player_character_class enum('WARRIOR', 'MAGE', 'ROGUE', 'CLERIC', 'RANGER', 'INVENTOR', 'ALCHEMIST', 'BARD', 'INQUISITOR') NOT NULL,
    player_character_level INT NOT NULL,
    player_character_experience BIGINT NOT NULL,
    player_character_health INT NOT NULL,
    player_character_mana INT NOT NULL,
    player_character_strength INT NOT NULL,
    player_character_agility INT NOT NULL,
    player_character_intelligence INT NOT NULL,
    player_character_spirit INT NOT NULL,
    player_character_stamina INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TRIGGER before_insert_player_character
BEFORE INSERT ON tempest_vortex_player_characters
FOR EACH ROW
SET NEW.player_character_id = IFNULL(NEW.player_character_id, UUID());

INSERT INTO tempest_vortex_player_characters (player_character_name, player_character_class, player_character_level, player_character_experience, player_character_health, player_character_mana, player_character_strength, player_character_agility, player_character_intelligence, player_character_spirit, player_character_stamina)
VALUES
    ('Arin the Brave', 'WARRIOR', 10, 1500, 200, 50, 20, 15, 5, 10, 25),
    ('Luna the Swift', 'ROGUE', 12, 1800, 150, 80, 15, 25, 10, 12, 18),
    ('Eldrin the Wise', 'MAGE', 15, 2500, 100, 200, 5, 10, 30, 20, 15),
    ('Mira the Healer', 'CLERIC', 13, 2000, 120, 150, 10, 12, 25, 30, 20),
    ('Gorak the Strong', 'WARRIOR', 11, 1600, 220, 40, 25, 10, 5, 8, 30), -- Changed from 'Barbarian'
    ('Thalia the Fierce', 'RANGER', 11, 1600, 180, 60, 18, 22, 8, 15, 20),
    ('Jaxon the Mason', 'INVENTOR', 14, 2200, 170, 90, 12, 18, 20, 15, 22);

