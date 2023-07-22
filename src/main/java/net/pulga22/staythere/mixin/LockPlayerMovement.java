package net.pulga22.staythere.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.pulga22.staythere.util.ILockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class LockPlayerMovement {

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerMove", at = @At("HEAD"), cancellable = true)
    public void lockMovement(PlayerMoveC2SPacket packet, CallbackInfo ci){
        if (((ILockData) player).staythere$isLocked()){
            this.player.setYaw(MathHelper.wrapDegrees(packet.getYaw(this.player.getYaw())) % 360.0F);
            this.player.setPitch(MathHelper.clamp(MathHelper.wrapDegrees(packet.getPitch(this.player.getPitch())), -90.0F, 90.0F) % 360.0F);
            ci.cancel();
        }
    }


}