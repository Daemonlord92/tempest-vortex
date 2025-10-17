# Migration and Validation Summary

## Overview
This document summarizes the Flyway migrations created and validation annotations added to all entities in the Tempest Vortex backend.

---

## New Flyway Migrations Created

### V3__add_audit_fields_to_existing_tables.sql
- Adds missing audit fields (created_at, updated_at, deleted_at, is_deleted) to:
  - `tempest_vortex_items`
  - `tempest_vortex_monsters`
- Renames `monster_id` to `id` for consistency
- Renames `loot_table_entry_id` to `id` for consistency

### V4__create_spell_table.sql
- Creates `tempest_vortex_spells` table
- Includes all spell effect types (FIRE, ICE, LIGHTNING, etc.)
- Adds constraints for mana_cost, cooldown, and level_requirement
- Includes 10 sample spells with diverse effects

### V5__create_combat_area_table.sql
- Creates `tempest_vortex_combat_areas` table
- Supports all habitat types (FOREST, DESERT, MOUNTAINS, etc.)
- Adds level requirement constraints (1-100)
- Includes 10 sample combat areas across different habitats

### V6__create_town_and_location_tables.sql
- Creates `tempest_vortex_towns` table
- Creates `tempest_vortex_locations` table
- Creates junction tables:
  - `tempest_vortex_location_biomes` (for storing multiple biomes per location)
  - `tempest_vortex_location_towns` (many-to-many relationship)
  - `tempest_vortex_location_combat_areas` (many-to-many relationship)
- Includes sample data for towns and locations

### V7__create_shopkeeper_table.sql
- Creates `tempest_vortex_shopkeepers` table
- Creates junction table `tempest_vortex_shopkeeper_inventory` with:
  - stock_quantity field
  - price_modifier field (for dynamic pricing)
- Includes 5 sample shopkeepers with different specializations

---

## Validation Annotations Added

### All Entities Now Include:

#### **PlayerCharacter**
- `@NotBlank` on name (3-150 characters)
- `@NotNull` on playerClass
- `@Min/@Max` on level (1-100)
- `@Min` on experience (≥0)
- `@Min` on all stats (health ≥1, mana ≥0, all other stats ≥1)

#### **Item**
- `@NotBlank` on name (1-255 characters)
- `@NotNull` on type and rarity
- `@Min/@Max` on levelRequirement (1-100)
- `@Min` on maxStackSize (≥1)
- `@Min` on value (≥0)
- `@Size` on description (max 1000 characters)

#### **Monster**
- Inherits validations from NonPlayerCharacter
- `@NotNull` on habitat with `@Enumerated(EnumType.STRING)`
- `@Min` on experiencePoints (≥0)
- `@Min/@Max` on lootDropRate (0-100%)

#### **NonPlayerCharacter** (Abstract Class)
- `@NotBlank` on name (1-255 characters)
- `@NotNull` on role with `@Enumerated`
- `@Min/@Max` on level (1-100)
- `@Size` on description (max 1000 characters)
- `@Min` on healthPoints, attackPower, defense (≥0)

#### **Spell**
- `@NotBlank` on name (1-255 characters)
- `@Size` on description (max 1000 characters)
- `@Min` on manaCost, cooldown (≥0)
- `@Min/@Max` on levelRequirement (1-100)
- `@Min` on positiveEffect and negativeEffect (≥0)
- `@NotNull` on effectType with `@Enumerated`

#### **CombatArea**
- `@NotBlank` on name (3-255 characters)
- `@Size` on description (max 1000 characters)
- `@NotNull` on habitat
- `@Min/@Max` on levelRequirement (1-100)

#### **Shopkeeper**
- `@NotBlank` on name (1-255 characters)
- `@Size` on description (max 1000 characters)
- Proper `@ManyToMany` relationship with Items via inventory junction table

#### **Town**
- `@NotBlank` on name (1-255 characters)
- `@Size` on description (max 1000 characters)
- `@OneToMany` relationship with NonPlayerCharacter

#### **Location**
- `@NotBlank` on name (1-255 characters)
- `@Size` on description (max 1000 characters)
- `@ElementCollection` for biome list with proper `@CollectionTable`
- `@ManyToMany` relationships for towns and combatAreas

#### **LootTableEntry**
- `@NotNull` on monster and item relationships
- `@Min/@Max` on dropChance (0-100%)
- `@Min` on minQuantity and maxQuantity (≥1)

---

## Database Constraints Added

All migrations include appropriate constraints:
- CHECK constraints for percentage values (0-100)
- CHECK constraints for level requirements (1-100)
- Foreign key relationships with CASCADE deletes where appropriate
- NOT NULL constraints on required fields
- Default values for audit fields

---

## Entity Relationship Improvements

1. **Proper JPA Mappings**: All relationships now use appropriate JPA annotations
2. **Junction Tables**: Created proper many-to-many junction tables with additional fields
3. **Cascade Operations**: Configured appropriate cascade behaviors
4. **Enum Handling**: All enums use `@Enumerated(EnumType.STRING)` for better readability

---

## Next Steps

1. **Run the migrations**: Execute `./gradlew flywayMigrate` to apply all migrations
2. **Test validation**: Ensure validation works by trying to create invalid entities
3. **Add custom validators**: Consider adding custom validation logic for business rules (e.g., maxQuantity > minQuantity)
4. **Add indexes**: Consider adding database indexes on frequently queried fields
5. **Consider soft deletes**: All entities have soft delete support via the AuditableEntity

---

## Notes

- All table warnings in the IDE are expected until migrations are run
- The column name in LootTableEntry uses `monster_id` to match the database
- Shopkeeper inventory is now properly modeled with stock and pricing information
- Location biomes use ElementCollection for the list of habitats

