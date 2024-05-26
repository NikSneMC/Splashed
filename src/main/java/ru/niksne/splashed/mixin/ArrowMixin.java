package ru.niksne.splashed.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
class ArrowMixin {
    @Shadow
    private Potion potion;

    @Inject(method = "onHit(Lnet/minecraft/entity/LivingEntity;)V", at = @At("RETURN"))
    protected void onHit(LivingEntity target, CallbackInfo ci) {
        if (this.potion == Potions.WATER) target.extinguish();
    }
}
