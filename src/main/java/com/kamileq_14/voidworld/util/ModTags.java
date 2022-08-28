package com.kamileq_14.voidworld.util;

import com.kamileq_14.voidworld.VoidWorld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                = tag("portal_frame_blocks");











        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(VoidWorld.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DIAMOND = forgeTag("diamond");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(VoidWorld.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class ConfiguredStructureFeatures {
        public static final TagKey<ConfiguredStructureFeature<?, ?>> CUSTOM_STRUCTURE_TAG = tag("custom_structure_tag");

        private static TagKey<ConfiguredStructureFeature<?, ?>> tag(String name) {
            return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(VoidWorld.MOD_ID, name));
        }
    }
}
