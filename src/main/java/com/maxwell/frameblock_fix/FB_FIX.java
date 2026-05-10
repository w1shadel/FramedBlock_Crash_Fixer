package com.maxwell.frameblock_fix;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FB_FIX.MODID)
public class FB_FIX
{

    public static final String MODID = "frameblock_fix";
    public FB_FIX(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
    }
}
