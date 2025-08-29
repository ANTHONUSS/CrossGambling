package fr.anthonus.crossgambling.commands;

import com.mojang.brigadier.context.CommandContext;
import fr.anthonus.crossgambling.Crossgambling;
import fr.anthonus.crossgambling.cross.Cross;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrossCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("cross")
                            .then(
                                    CommandManager.argument("position", BlockPosArgumentType.blockPos())
                                            .executes(CrossCommand::execute)

                                            .then(
                                                    CommandManager.argument("block", BlockStateArgumentType.blockState(registryAccess))
                                                            .executes(CrossCommand::executeWithBlock)
                                            )
                            )
            );
        });
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) {
        World world = ctx.getSource().getWorld();
        BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "position");

        Cross.spawnCross(world, pos, null);

        Crossgambling.LOGGER.info("Random cross spawned");

        return 1;
    }

    private static int executeWithBlock(CommandContext<ServerCommandSource> ctx) {
        World world = ctx.getSource().getWorld();
        BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "position");
        BlockState state = BlockStateArgumentType.getBlockState(ctx, "block").getBlockState();

        Cross.spawnCross(world, pos, state);

        Crossgambling.LOGGER.info("Cross spawned with block " + state.getBlock().getTranslationKey());

        return 1;
    }
}
