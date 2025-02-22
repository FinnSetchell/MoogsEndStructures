![banner](https://www.bisecthosting.com/images/CF/MES/BH_ME_HEADER.webp)

***

[![Support me on Ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/D1D8LKA5N) [![Discord](https://img.shields.io/discord/869218732650688543?color=3e0e49&label=DISCORD&style=for-the-badge)](https://discord.com/invite/S5nffJbuvA) [![My projects](https://img.shields.io/badge/My-projects-3e0e49?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/members/finndog_123/projects)

***

![Overview](https://www.bisecthosting.com/images/CF/MES/BH_ME_BANNER1.webp)

***

<div style="background-color:#3e0e49;color:#fff;text-align:center">Feedback</div>

Please comment any ideas you may have to improve this mod. Any and all feedback is greatly appreciated :)

Additionally, if you make any configs you think other people would enjoy, send it to me on Discord and I'll be happy to upload it here!

<div style="background-color:#3e0e49;color:#fff;text-align:center">NeoForge and Fabric</div>

All datapack versions work on Forge, NeoForge **and** Fabric.

<div style="background-color:#3e0e49;color:#fff;text-align:center">How To Use</div>

This is a template datapack for my structure mod, Moogs End Structures. Look at the info below to see how to use it.

This guide will help you customize the Moog's End Structures datapack by explaining how to:

1.  Remove unwanted structures
2.  Change the rarity of structures
3.  Modify biome tags

## 1\. Removing Unwanted Structures

To remove unwanted structures, you need to modify the relevant structure files in the `data/mes/worldgen/structure_set` directory and remove the section spawning the structure.

For example, to remove the "astral\_hideaway" structure:

*   Open the file `data/mes/worldgen/structure_set/other_decoration/astral_hideaway.json`.
*   Modify the JSON to remove the structure spawning section, so it looks like this:

```
{
  "structures":[

  ],
  "placement": {
    "type": "mes:advanced_random_spread",
    "salt": 136568741,
    "spacing": 25,
    "separation": 15,
    "min_distance_from_world_origin": 1000
  }
}
```

## 2\. Changing the Rarity of Structures

To change the rarity of structures, modify the `spacing` and `separation` attributes in the relevant structure\_set files in the `data/mes/worldgen/structure_set` directory.

For example, to change the rarity of the "astral\_hideaway" structure:

*   Open the file `data/mes/worldgen/structure_set/other_decoration/astral_hideaway.json`.
*   Modify the `spacing` and `separation` attributes to adjust its rarity.

```
{
  "structures": [
    {
      "structure": "mes:astral_hideaway",
      "weight": 1
    }
  ],
  "placement": {
    "type": "mes:advanced_random_spread",
    "salt": 136568741,
    "spacing": 100, // Increase this value to make the structure less common
    "separation": 80 // Ensure this value is smaller than spacing
    "min_distance_from_world_origin": 1000
  }
}
```

Explanation of attributes:

*   `spacing`: Average distance between two neighboring generation attempts. Value between 0 and 4096 (inclusive).
*   `separation`: Minimum distance (in chunks) between two neighboring attempts. Value between 0 and 4096 (inclusive). Has to be strictly smaller than `spacing`. The maximum distance of two neighboring generation attempts is `2*spacing - separation`.

## 3\. Modifying Biome Tags

To modify biome tags, edit the relevant JSON files in the `data/mes/tags/worldgen/biome/has_structure` directory.

For example, to add or remove biomes from the "snowy\_biomes" tag:

*   Open the file `data/mes/tags/worldgen/biome/has_structure/end_biomes.json`.
*   Add or remove biomes from the `values` array.

```
{
  "replace": false,
  "values": [
    //add or remove biomes here
    "#minecraft:is_end",
    {
      "id": "#forge:is_end",
      "required": false
    },
    {
      "id": "#c:in_the_end",
      "required": false
    },
    {
      "id": "#c:end_islands",
      "required": false
    }
  ]
}
```

When modifying biome tags, note the following:

*   Biome IDs should be written as `"minecraft:snowy_taiga"`.
*   Biome tags should be written with a hashtag in front, such as `"#minecraft:is_jungle"`.
*   When adding modded biomes, they must be written with `"required": false`, like this:

```
  {
    "id": "#biomesoplenty:is_savanna",
    "required": false
  }
```

*   Make sure to set `"replace": true` to ensure the changes take effect.

***

![Banner](https://www.bisecthosting.com/images/CF/MES/BH_ME_BANNER3.webp)

The best and fastest way to get help is to join our [Discord server](https://discord.gg/S5nffJbuvA).

[![Discord](https://i.imgur.com/sfAmR3Y.png)](https://discord.gg/S5nffJbuvA)

***

[![BHsponser](https://www.bisecthosting.com/images/CF/MES/BH_ME_PROMO.webp)](https://bisecthosting.com/moogsmods)