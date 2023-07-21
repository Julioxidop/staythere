package net.pulga22.staythere.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.pulga22.staythere.util.ILockData;

public class SyncS2CPacket {
    public static void onClient(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler,
                                PacketByteBuf buf, PacketSender packetSender) {

        PlayerEntity player = client.player;
        if (player == null) return;

        boolean newState = buf.readBoolean();
        ((ILockData) player).staythere$setLocked(newState);

    }
}
