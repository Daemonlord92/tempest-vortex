-- Add missing audit fields to tables created in V2
ALTER TABLE tempest_vortex_items
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ADD COLUMN deleted_at TIMESTAMP NULL,
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE tempest_vortex_monsters
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ADD COLUMN deleted_at TIMESTAMP NULL,
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN town_id CHAR(36);

-- Rename monster_id to id for consistency
ALTER TABLE tempest_vortex_monsters
    CHANGE COLUMN monster_id id CHAR(36);

-- Rename loot_table_entry_id to id for consistency
ALTER TABLE tempest_vortex_loot_table_entries
    CHANGE COLUMN loot_table_entry_id id CHAR(36);
