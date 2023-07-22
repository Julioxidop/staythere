package net.pulga22.staythere;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.pulga22.staythere.networking.ChangeLockedStateS2CPacket;
import net.pulga22.staythere.networking.Packets;

public class StayThereClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.CHANGE_STATE, ChangeLockedStateS2CPacket::onClient);
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client)->{
            if (client.player != null){
                ClientPlayNetworking.send(Packets.REQUEST_SYNC_C2S, PacketByteBufs.empty());
            }
        });
    }
}
