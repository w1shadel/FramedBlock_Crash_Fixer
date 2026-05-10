package com.maxwell.frameblock_fix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xfacthd.framedblocks.api.model.util.ModelUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(value = ModelUtils.class, remap = false)
public abstract class MixinModelUtils {

    @Inject(method = "getAllCullableQuads", at = @At("HEAD"), cancellable = true)
    private static void onSafeGetAllCullableQuads(BakedModel model, BlockState state, RandomSource rand, ModelData data, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir) {
        if (model == null) {
            ArrayList<BakedQuad> missingQuads = new ArrayList<>();

            try {
                BakedModel missingModel = Minecraft.getInstance().getModelManager().getMissingModel();
                for (Direction dir : Direction.values()) {
                    missingQuads.addAll(missingModel.getQuads(state, dir, rand, data, renderType));
                }
                missingQuads.addAll(missingModel.getQuads(state, null, rand, data, renderType));

            } catch (Exception e) {
                e.printStackTrace();
            }
            cir.setReturnValue(missingQuads);
        }
    }
}