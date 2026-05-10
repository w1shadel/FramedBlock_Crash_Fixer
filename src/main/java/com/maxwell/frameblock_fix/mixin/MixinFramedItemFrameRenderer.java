package com.maxwell.frameblock_fix.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xfacthd.framedblocks.client.render.block.FramedItemFrameRenderer;

@Mixin(value = FramedItemFrameRenderer.class, remap = false)
public abstract class MixinFramedItemFrameRenderer {
    @Redirect(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderStatic(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;IILcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;I)V")
    )
    private void safeRenderStatic(ItemRenderer instance, ItemStack stack, ItemDisplayContext context, int packedLight, int packedOverlay, PoseStack poseStack, MultiBufferSource buffer, Level level, int seed) {
        try {

            instance.renderStatic(stack, context, packedLight, packedOverlay, poseStack, buffer, level, seed);
        } catch (Throwable t) {


        }
    }
}