package net.akaneo.fastfooddelight.common.registry;

import net.akaneo.fastfooddelight.FastFoodDelight;
import net.akaneo.fastfooddelight.common.block.CheckoutMachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FFBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(FastFoodDelight.MODID);

    public static final DeferredHolder<Block, Block> CHECKOUT_MACHINE = BLOCKS.register("checkout_machine",
            () -> new CheckoutMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
}
