-- Create Spell table
CREATE TABLE tempest_vortex_spells (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    mana_cost INT NOT NULL,
    cooldown INT NOT NULL DEFAULT 0,
    level_requirement INT NOT NULL DEFAULT 1,
    positive_effect INT NOT NULL DEFAULT 0,
    negative_effect INT NOT NULL DEFAULT 0,
    effect_type ENUM('FIRE','ICE','LIGHTNING','POISON','HEALING','SHIELD','SUMMONING','CURSE','BUFF','DEBUFF','VOID','HOLY','DARK','ARCANE','NATURE','WATER','WIND','EARTH','TIME','SPACE','MIND','VAMPIRIC','CHAOS') NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_mana_cost CHECK (mana_cost >= 0),
    CONSTRAINT chk_cooldown CHECK (cooldown >= 0),
    CONSTRAINT chk_level_requirement CHECK (level_requirement >= 1 AND level_requirement <= 100)
);

-- Insert sample spells
INSERT INTO tempest_vortex_spells (id, name, description, mana_cost, cooldown, level_requirement, positive_effect, negative_effect, effect_type)
VALUES
(UUID(), 'Fireball', 'Hurls a ball of fire at the enemy', 30, 2, 5, 0, 50, 'FIRE'),
(UUID(), 'Healing Light', 'Restores health to the caster', 25, 3, 3, 60, 0, 'HEALING'),
(UUID(), 'Ice Shard', 'Launches sharp ice projectiles', 20, 1, 4, 0, 35, 'ICE'),
(UUID(), 'Lightning Bolt', 'Strikes enemy with lightning', 40, 3, 7, 0, 70, 'LIGHTNING'),
(UUID(), 'Poison Cloud', 'Creates a cloud of poison', 35, 4, 6, 0, 45, 'POISON'),
(UUID(), 'Divine Shield', 'Creates a protective barrier', 50, 5, 10, 100, 0, 'SHIELD'),
(UUID(), 'Dark Curse', 'Weakens the enemy', 45, 3, 8, 0, 40, 'CURSE'),
(UUID(), 'Arcane Missiles', 'Launches multiple magic projectiles', 25, 2, 3, 0, 30, 'ARCANE'),
(UUID(), 'Nature\'s Blessing', 'Enhances natural abilities', 30, 4, 5, 50, 0, 'NATURE'),
(UUID(), 'Vampiric Touch', 'Drains life from enemy', 40, 3, 9, 35, 35, 'VAMPIRIC');

