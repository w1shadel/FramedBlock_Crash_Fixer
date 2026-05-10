package com.maxwell.frameblock_fix.mixin;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xfacthd.framedblocks.client.model.FluidModel;

@Mixin(value = FluidModel.class, remap = false)
public abstract class MixinFluidModel {

    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void onCreate(Fluid fluid, CallbackInfoReturnable<FluidModel> cir) {
        if (fluid == null) {
            cir.setReturnValue(null);
            return;
        }
        try {
            if (fluid.getFluidType() == null || ForgeRegistries.FLUID_TYPES.get().getKey(fluid.getFluidType()) == null) {
                cir.setReturnValue(null);
            }
        } catch (Exception e) {
            cir.setReturnValue(null);
        }
    }
}