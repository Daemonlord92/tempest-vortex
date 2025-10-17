-- Add foreign key constraints for town relationships
-- This migration runs after towns table is created in V6

ALTER TABLE tempest_vortex_monsters
    ADD CONSTRAINT fk_monster_town FOREIGN KEY (town_id) REFERENCES tempest_vortex_towns(id) ON DELETE SET NULL;

ALTER TABLE tempest_vortex_shopkeepers
    ADD CONSTRAINT fk_shopkeeper_town FOREIGN KEY (town_id) REFERENCES tempest_vortex_towns(id) ON DELETE SET NULL;

