package com.kamileq_14.voidworld;

import com.kamileq_14.voidworld.block.ModBlocks;
import com.kamileq_14.voidworld.fluid.ModFluids;
import com.kamileq_14.voidworld.item.ModItems;
import com.kamileq_14.voidworld.particle.ModParticles;
import com.kamileq_14.voidworld.util.ModBlockEntities;
import com.kamileq_14.voidworld.util.ModPOIs;
import com.kamileq_14.voidworld.world.dimension.ModDimensions;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VoidWorld.MOD_ID)
public class VoidWorld {
    public static final String MOD_ID = "voidworld";
    private static final Logger LOGGER = LogUtils.getLogger();


    public VoidWorld() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModDimensions.register();
        ModPOIs.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModParticles.register(eventBus);
        ModFluids.register(eventBus);
        ModBlockEntities.register(eventBus);




        eventBus.addListener(this::setup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    //private void clientSetup (final FMLClientSetupEvent event) {

       // ModItemProperties.addCustomItemProperties();





 //   }


    private void clientSetup(final FMLClientSetupEvent event)  {


        ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_FLOWING.get(), RenderType.translucent());





    }


    private void setup(final FMLCommonSetupEvent event)
    {

        //  ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_BLOCK.get(), RenderType.translucent());
       // ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_FLUID.get(), RenderType.translucent());
        //ItemBlockRenderTypes.setRenderLayer(ModFluids.VOID_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLUID_TANK.get(), RenderType.translucent());


        ItemBlockRenderTypes.setRenderLayer(ModBlocks.VOID_PORTAL.get(), RenderType.translucent());
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}