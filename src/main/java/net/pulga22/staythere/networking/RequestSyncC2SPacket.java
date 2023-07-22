package net.pulga22.staythere.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pulga22.staythere.util.ILockData;

public class RequestSyncC2SPacket {
    public static void onServer(MinecraftServer server, ServerPlayerEntity player,
                                ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender packetSender) {

        server.execute(() -> {
            boolean state = ((ILockData) player).staythere$isLocked();
            PacketByteBuf buf1 = PacketByteBufs.create();
            buf1.writeBoolean(state);
            ServerPlayNetworking.send(player, Packets.CHANGE_STATE, buf1);
        });

    }
}
