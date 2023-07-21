package net.pulga22.staythere.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.pulga22.staythere.util.ILockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityData implements ILockData {

    @Unique boolean lockedMovement = false;

    //Nbt -> data
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomNbt(NbtCompound nbt, CallbackInfo ci){
        this.lockedMovement = nbt.getBoolean("lockedMovement");
    }

    //Data -> bt
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putBoolean("lockedMovement", this.lockedMovement);
    }

    @Override
    public void staythere$setLocked(boolean locked) {
        this.lockedMovement = locked;
    }

    @Override
    public boolean staythere$isLocked() {
        return this.lockedMovement;
    }
}
