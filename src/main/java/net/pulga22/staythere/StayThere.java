package net.pulga22.staythere;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.pulga22.staythere.commands.LockMovementCommand;
import net.pulga22.staythere.networking.Packets;
import net.pulga22.staythere.networking.RequestSyncC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StayThere implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("staythere");

	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(Packets.REQUEST_SYNC_C2S, RequestSyncC2SPacket::onServer);
		CommandRegistrationCallback.EVENT.register(LockMovementCommand::register);
		LOGGER.info("Stay where are you at!");
	}
}