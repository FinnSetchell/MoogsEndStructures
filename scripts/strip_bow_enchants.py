"""
Strip power and flame enchantments from stray bows in structure NBT files.

Works on both old format (tag.Enchantments list) and new format
(components."minecraft:enchantments".levels map).

Also strips non-standard direct enchantment Int tags (power, flame)
from the old tag compound.
"""

import sys
import os
import nbtlib
from pathlib import Path

ENCHANTS_TO_REMOVE = {"minecraft:power", "minecraft:flame", "power", "flame"}

def strip_bow_enchants_old_format(item):
    """Remove power/flame from old-format item (tag.Enchantments list + direct tags)."""
    tag = item.get("tag")
    if not tag:
        return False

    changed = False

    # Remove direct Int tags like power: 5, flame: 1
    for key in list(tag.keys()):
        if key in ("power", "flame"):
            del tag[key]
            changed = True

    # Remove from Enchantments list
    if "Enchantments" in tag:
        original_len = len(tag["Enchantments"])
        tag["Enchantments"] = nbtlib.List([
            e for e in tag["Enchantments"]
            if str(e.get("id", "")) not in ENCHANTS_TO_REMOVE
        ])
        if len(tag["Enchantments"]) != original_len:
            changed = True
        if len(tag["Enchantments"]) == 0:
            del tag["Enchantments"]

    return changed

def strip_bow_enchants_new_format(item):
    """Remove power/flame from new-format item (components."minecraft:enchantments".levels)."""
    components = item.get("components")
    if not components:
        return False

    ench_key = "minecraft:enchantments"
    if ench_key not in components:
        return False

    levels = components[ench_key].get("levels")
    if not levels:
        return False

    changed = False
    for key in list(levels.keys()):
        if str(key) in ENCHANTS_TO_REMOVE:
            del levels[key]
            changed = True

    if len(levels) == 0:
        del components[ench_key]

    return changed

def process_entity(entity):
    changed = False
    hand_items = entity.get("HandItems")
    if not hand_items:
        return False

    for item in hand_items:
        item_id = str(item.get("id", ""))
        if "bow" not in item_id:
            continue

        if "tag" in item:
            changed |= strip_bow_enchants_old_format(item)
        if "components" in item:
            changed |= strip_bow_enchants_new_format(item)

    return changed

def process_nbt_file(filepath):
    nbt = nbtlib.load(filepath)
    entities = nbt.get("entities", [])
    changed = False

    for entry in entities:
        entity_nbt = entry.get("nbt", {})
        if process_entity(entity_nbt):
            changed = True

    if changed:
        nbt.save(filepath)
        print(f"  MODIFIED: {filepath}")
    return changed

def main():
    if len(sys.argv) < 2:
        print("Usage: python strip_bow_enchants.py <nbt_directory> [<nbt_directory2> ...]")
        sys.exit(1)

    total_modified = 0
    for directory in sys.argv[1:]:
        print(f"\nScanning: {directory}")
        for nbt_path in Path(directory).rglob("*.nbt"):
            try:
                if process_nbt_file(nbt_path):
                    total_modified += 1
            except Exception as e:
                print(f"  ERROR: {nbt_path}: {e}")

    print(f"\nDone. Modified {total_modified} file(s).")

if __name__ == "__main__":
    main()
