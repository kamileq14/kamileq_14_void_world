package com.kamileq_14.voidworld.item;


import com.kamileq_14.voidworld.VoidWorld;
import com.kamileq_14.voidworld.fluid.ModFluids;
import com.kamileq_14.voidworld.item.custom.CatalystItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VoidWorld.MOD_ID);

    public static final RegistryObject<Item> VOID_CATALYST = ITEMS.register("void_catalyst", CatalystItem::new);


    public static final RegistryObject<Item> VOID_BUCKET = ITEMS.register("void_bucket",
            () -> new BucketItem(ModFluids.VOID_FLUID,
                    new Item.Properties().tab(ModCreativeModeTab.VOIDWORLD_TAB).stacksTo(1)));








    public static void register(IEventBus eventBus)  {
        ITEMS.register(eventBus);
}


}
