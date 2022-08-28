package com.kamileq_14.voidworld.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModCreativeModeTab {

    public static final CreativeModeTab VOIDWORLD_TAB = new CreativeModeTab("voidworldtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.VOID_CATALYST.get());
        }
    };
}
