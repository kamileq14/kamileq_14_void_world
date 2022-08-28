package com.kamileq_14.voidworld.fluid;

import com.kamileq_14.voidworld.VoidWorld;
import com.kamileq_14.voidworld.block.ModBlocks;
import com.kamileq_14.voidworld.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, VoidWorld.MOD_ID);


    public static final RegistryObject<FlowingFluid> VOID_FLUID
            = FLUIDS.register("void_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.VOID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> VOID_FLOWING
            = FLUIDS.register("void_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.VOID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties VOID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> VOID_FLUID.get(), () -> VOID_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).temperature(1000).sound(SoundEvents.ANVIL_BREAK).overlay(WATER_OVERLAY_RL)
            .color(0x584ae3)).slopeFindDistance(1).levelDecreasePerBlock(1)
            .block(() -> ModFluids.VOID_BLOCK.get()).bucket(() -> ModItems.VOID_BUCKET.get());

    public static final RegistryObject<LiquidBlock> VOID_BLOCK = ModBlocks.BLOCKS.register("void",
            () -> new LiquidBlock(() -> ModFluids.VOID_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}