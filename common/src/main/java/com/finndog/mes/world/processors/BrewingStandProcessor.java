package com.finndog.mes.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import com.finndog.mes.modinit.MESProcessors;

import net.minecraft.util.RandomSource;

public class BrewingStandProcessor extends StructureProcessor {
    public static final Codec<BrewingStandProcessor> CODEC = RecordCodecBuilder.create(instance -> 
        instance.group(
            Codec.FLOAT.fieldOf("brewing_stand_chance").orElse(1.0f).forGetter(processor -> processor.brewingStandChance),
            Codec.INT.fieldOf("min_potions").orElse(1).forGetter(processor -> processor.minPotions),
            Codec.INT.fieldOf("max_potions").orElse(3).forGetter(processor -> processor.maxPotions)
        ).apply(instance, BrewingStandProcessor::new)
    );

    private final float brewingStandChance;
    private final int minPotions;
    private final int maxPotions;

    public BrewingStandProcessor(float brewingStandChance, int minPotions, int maxPotions) {
        this.brewingStandChance = brewingStandChance;
        this.minPotions = minPotions;
        this.maxPotions = maxPotions;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos pos, BlockPos pos2, 
                                                          StructureTemplate.StructureBlockInfo blockInfo, 
                                                          StructureTemplate.StructureBlockInfo relativeBlockInfo, 
                                                          StructurePlaceSettings settings) {
        // Only process brewing stands - item frames are entities, not blocks
        if (relativeBlockInfo.state().is(Blocks.BREWING_STAND)) {
            return processBrewingStand(relativeBlockInfo, settings);
        }
        
        return relativeBlockInfo;
    }



    private StructureTemplate.StructureBlockInfo processBrewingStand(StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings settings) {
        RandomSource random = settings.getRandom(blockInfo.pos());
        float chance = random.nextFloat();
        if (chance < brewingStandChance) {
            CompoundTag nbt = blockInfo.nbt() != null ? blockInfo.nbt().copy() : new CompoundTag();
            
            ListTag itemsList = new ListTag();
            
            // Random number of potions between min and max
            int numPotions = random.nextInt(minPotions, maxPotions + 1);
            
            // Available potion types for variety
            String[] potionTypes = {
                "minecraft:regeneration",
                "minecraft:swiftness", 
                "minecraft:fire_resistance",
                "minecraft:healing",
                "minecraft:night_vision",
                "minecraft:strength"
            };
            
            // Fill random slots with random potions
            for (int i = 0; i < numPotions; i++) {
                CompoundTag slot = new CompoundTag();
                slot.putByte("Slot", (byte) i);
                slot.putString("id", "minecraft:potion");
                slot.putByte("Count", (byte) 1);
                
                CompoundTag potionTag = new CompoundTag();
                potionTag.putString("Potion", potionTypes[random.nextInt(potionTypes.length)]);
                slot.put("tag", potionTag);
                
                itemsList.add(slot);
            }
            
            nbt.put("Items", itemsList);
            
            return new StructureTemplate.StructureBlockInfo(blockInfo.pos(), blockInfo.state(), nbt);
        }
        
        return blockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return MESProcessors.BREWING_STAND_PROCESSOR.get();
    }
}
