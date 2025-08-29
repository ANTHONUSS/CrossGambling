package fr.anthonus.crossgambling.cross;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cross {
    private static final Random RANDOM = new Random();

    public static void spawnCross(World world, BlockPos pos, BlockState state) {
        if (state == null) {
            state = getRandomSolidBlock();
        }

        world.setBlockState(pos, state);
        pos = pos.up();
        world.setBlockState(pos, state);
        pos = pos.up();
        world.setBlockState(pos, state);
        pos = pos.up();
        world.setBlockState(pos, state);
        pos = pos.down().east();
        world.setBlockState(pos, state);
        pos = pos.west().west();
        world.setBlockState(pos, state);
    }

    private static BlockState getRandomSolidBlock() {
        List<Block> solidBlocks = new ArrayList<>();

        for (Block block : Registries.BLOCK) {
            if (block != Blocks.AIR && block.getDefaultState().isOpaqueFullCube()) {
                solidBlocks.add(block);
            }
        }

        if (solidBlocks.isEmpty()) return Blocks.STONE.getDefaultState();
        return solidBlocks.get(RANDOM.nextInt(solidBlocks.size())).getDefaultState();
    }
}
