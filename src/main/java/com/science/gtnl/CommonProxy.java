package com.science.gtnl;

import static com.science.gtnl.ScienceNotLeisure.network;

import net.minecraftforge.common.MinecraftForge;

import com.science.gtnl.Utils.GlobalSteamWorldSavedData;
import com.science.gtnl.Utils.PlayerUtils;
import com.science.gtnl.Utils.WorldUtils;
import com.science.gtnl.Utils.message.PacketGetTileEntityNBTRequest;
import com.science.gtnl.Utils.message.PacketTileEntityNBT;
import com.science.gtnl.Utils.message.TitlePacket;
import com.science.gtnl.asm.TickrateMessage;
import com.science.gtnl.common.block.blocks.playerDoll.PlayerDollWaila;
import com.science.gtnl.common.machine.hatch.SuperCraftingInputHatchME;
import com.science.gtnl.config.ClientEventHandler;
import com.science.gtnl.config.ConfigSyncMessage;
import com.science.gtnl.config.ServerEventHandler;

import appeng.api.AEApi;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance()
            .bus()
            .register(new ServerEventHandler());
        FMLCommonHandler.instance()
            .bus()
            .register(new ClientEventHandler());

        network.registerMessage(TitlePacket.Handler.class, TitlePacket.class, 0, Side.CLIENT);
        network.registerMessage(TickrateMessage.Handler.class, TickrateMessage.class, 1, Side.CLIENT);
        network.registerMessage(ConfigSyncMessage.Handler.class, ConfigSyncMessage.class, 2, Side.CLIENT);
        network.registerMessage(
            PacketGetTileEntityNBTRequest.Handler.class,
            PacketGetTileEntityNBTRequest.class,
            3,
            Side.SERVER);
        network.registerMessage(PacketTileEntityNBT.Handler.class, PacketTileEntityNBT.class, 4, Side.CLIENT);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GlobalSteamWorldSavedData(""));
        MinecraftForge.EVENT_BUS.register(new PlayerUtils());
        MinecraftForge.EVENT_BUS.register(new WorldUtils());
        FMLCommonHandler.instance()
            .bus()
            .register(new WorldUtils());
        FMLCommonHandler.instance()
            .bus()
            .register(new PlayerUtils());
        PlayerDollWaila.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        AEApi.instance()
            .registries()
            .interfaceTerminal()
            .register(SuperCraftingInputHatchME.class);
    }
}
