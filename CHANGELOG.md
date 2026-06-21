# Changelog

---

## [2.0.5] - 2026-06-21

### Changed
- mc 26.2 support

---

## [2.0.4] - 2026-05-22

_Pending. Update this header date and replace this line with the actual changes before tagging._

---

## [2.0.3] - 2026-05-22

### Fixed
- Versioned structures now have a defined path for Minecraft 26.1–26.1.2, so the game stops logging "no version mapping matched" warnings and no longer falls back to an older structure template.

---

## [2.0.2] - 2026-05-03

### Added
- Added 3 custom End loot tables (end_common, end_uncommon, end_rare) and applied them to all structures
- Filled empty barrels in the Deepslate Mega Ships with loot

### Fixed
- Removed a broken template pool from the Crashed Deepslate Mega Ship that referenced NBT files which don't exist

### Changed
- Consolidated all mega ship NBTs from versioned folders (v1_21_0_4, v1_21_5_10) into a single `mega_ship/` subfolder as files were identical across versions
- Removed versioned pool elements from all mega ship pools except mega_ship_deepslate_2/side_pool (which still needs versioning for the chain -> iron_chain rename in 1.21.9)

### Removed
- Removed 18 unused loot table files that were leftover from earlier development

---

## [2.0.1] - 2026-05-01

### Changed
- Updated for 1.21.11

---
