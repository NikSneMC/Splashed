package ru.niksne.splashed.mixin;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
abstract
class ArrowMixin {

    @Shadow protected abstract PotionContentsComponent getPotionContents();

    @Inject(method = "onHit(Lnet/minecraft/entity/LivingEntity;)V", at = @At("RETURN"))
    protected void onHit(LivingEntity target, CallbackInfo ci) {
        if (this.getPotionContents().potion().isPresent() && this.getPotionContents().potion().get() == Potions.WATER)
            target.extinguishWithSound();
    }
}
