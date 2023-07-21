package net.pulga22.staythere.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import net.pulga22.staythere.util.ILockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityData {

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void cancelMove(MovementType movementType, Vec3d movement, CallbackInfo ci){
        if (((ILockData)((ClientPlayerEntity)(Object)this)).staythere$isLocked()){
            ci.cancel();
        }
    }

}
