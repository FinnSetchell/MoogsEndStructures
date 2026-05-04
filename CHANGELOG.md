# Changelog

---

## [2.0.3] - 2026-05-04

_Pending. Update this header date and replace this line with the actual changes before tagging._

---

## [2.0.2] - 2026-05-04

### Fixed
- Converted stray entity items from old NBT format (pre-1.20.5) to new component format for 1.20.5+ compatibility
- Added versioned structure pool elements for mega ship structures containing strays, serving old-format NBTs for 1.20-1.20.4 and new-format NBTs for 1.20.5-1.20.6

---

## [2.0.1] - 2026-05-03

### Added
- Added 3 new loot tables: `end_common`, `end_uncommon`, `end_rare`
- Assigned loot tables to containers in: enderwatch_tower, manuscript_shrine, monolith, mythic_garden, phantom_citadel, placid_prairie, starlight_voyager

### Removed
- Removed 21 unused overworld loot tables

---

## [2.0.0] - 2026-05-01

### Added
- Converted to datapack with dependency on MoogsStructureLib (moogs_structures)
- New loot tables for empty barrels: a minimal end-themed junk table and a rarer mid-tier variant
- All empty containers now have loot tables assigned

### Fixed
- All custom type IDs updated to use the correct moogs_structures namespace
- Replaced all vanilla end city treasure references with a custom equivalent
- Fixed sign NBT format incompatible with 1.20
- Removed broken entry from Mega Ship Crashed Deepslate's top pool
- Removed 5 unused processor lists

---
