package com.kamileq_14.voidworld.world.dimension;

import com.kamileq_14.voidworld.VoidWorld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> VOIDDIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(VoidWorld.MOD_ID,"voiddim"));
    public static final ResourceKey<DimensionType> VOIDDIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, VOIDDIM_KEY.getRegistryName());

    public static void register() {
     System.out.println("Registering ModDimension for " + VoidWorld.MOD_ID);

    }
}
