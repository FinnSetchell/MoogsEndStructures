package com.finndog.mes.modinit;

import com.finndog.mes.MESCommon;
import com.finndog.mes.modinit.registry.ResourcefulRegistries;
import com.finndog.mes.modinit.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class MESProcessors {
    public static final ResourcefulRegistry<StructureProcessorType<?>> STRUCTURE_PROCESSOR = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PROCESSOR, MESCommon.MODID);
}
