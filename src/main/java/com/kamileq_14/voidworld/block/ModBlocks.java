package com.kamileq_14.voidworld.block;

import com.kamileq_14.voidworld.VoidWorld;
import com.kamileq_14.voidworld.block.custom.ModFlammableRotatedPillarBlock;
import com.kamileq_14.voidworld.block.custom.VoidPortalBlock;
import com.kamileq_14.voidworld.block.custom.tank.FluidTankBlock;
import com.kamileq_14.voidworld.item.ModCreativeModeTab;
import com.kamileq_14.voidworld.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VoidWorld.MOD_ID);



    public static final RegistryObject<Block> FLUID_TANK = registerBlock("fluid_tank",
            () -> new FluidTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()),
            ModCreativeModeTab.VOIDWORLD_TAB);
    public static final RegistryObject<Block> VOID_PORTAL = registerBlockWithoutBlockItem("void_portal",
            VoidPortalBlock::new);

    public static final RegistryObject<Block> VOID_BRICKS_BLOCK = registerBlock("void_bricks_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.VOIDWORLD_TAB);

    public static final RegistryObject<Block> VOID_LOG = registerBlock("void_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)),
            ModCreativeModeTab.VOIDWORLD_TAB);

    public static final RegistryObject<Block> VOID_WOOD = registerBlock("void_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)),
            ModCreativeModeTab.VOIDWORLD_TAB);

    public static final RegistryObject<Block> STRIPPED_VOID_LOG = registerBlock("stripped_void_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)),
            ModCreativeModeTab.VOIDWORLD_TAB);

    public static final RegistryObject<Block> STRIPPED_VOID_WOOD = registerBlock("stripped_void_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)),
            ModCreativeModeTab.VOIDWORLD_TAB);


    public static final RegistryObject<Block> VOID_PLANKS = registerBlock("void_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {






                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, ModCreativeModeTab.VOIDWORLD_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
