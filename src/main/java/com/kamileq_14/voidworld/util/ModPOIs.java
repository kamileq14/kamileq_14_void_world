package com.kamileq_14.voidworld.util;

import com.kamileq_14.voidworld.VoidWorld;
import com.kamileq_14.voidworld.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, VoidWorld.MOD_ID);

    public static final RegistryObject<PoiType> VOID_PORTAL =
            POI.register("void_portal", () -> new PoiType("void_portal",
                    PoiType.getBlockStates(ModBlocks.VOID_PORTAL.get()), 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}