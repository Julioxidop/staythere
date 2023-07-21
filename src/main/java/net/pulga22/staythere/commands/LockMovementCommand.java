package net.pulga22.staythere.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pulga22.staythere.networking.Packets;
import net.pulga22.staythere.util.ILockData;

import java.util.Collection;

public class LockMovementCommand {

    private final static String BLOCKED = "Se ha bloqueado el movimiento a %d jugadores.";
    private final static String UNBLOCKED = "Se ha desbloqueado el movimiento a %d jugadores.";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registry,
                                CommandManager.RegistrationEnvironment env) {
        dispatcher.register(CommandManager.literal("staythere").requires(source -> source.hasPermissionLevel(2))
                .then(
                        CommandManager.argument("targets", EntityArgumentType.players())
                                .then(
                                        CommandManager.literal("lock")
                                                .executes(context ->
                                                        command(context.getSource(), EntityArgumentType.getPlayers(context, "targets"), true)
                                                )
                                )
                                .then(
                                        CommandManager.literal("unlock")
                                                .executes(context ->
                                                        command(context.getSource(), EntityArgumentType.getPlayers(context, "targets"), false)
                                                )
                                )
                )
        );
    }

    private static int command(ServerCommandSource source, Collection<ServerPlayerEntity> targets, boolean state){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(state);
        targets.forEach(player -> {
            ((ILockData) player).staythere$setLocked(state);
            ServerPlayNetworking.send(player, Packets.CHANGE_STATE, buf);
        });
        source.sendFeedback(Text.literal(String.format(state ? BLOCKED : UNBLOCKED, targets.size())), true);
        return 1;
    }
}
