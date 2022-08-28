package com.kamileq_14.voidworld.util;

import com.kamileq_14.voidworld.VoidWorld;
import com.kamileq_14.voidworld.block.ModBlocks;
import com.kamileq_14.voidworld.block.custom.tank.FluidTankTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, VoidWorld.MOD_ID);

    public static final RegistryObject<BlockEntityType<FluidTankTileEntity>> FLUID_TANK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("fluid_tank_block_entity", () ->
                    BlockEntityType.Builder.of(FluidTankTileEntity::new,
                            ModBlocks.FLUID_TANK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}