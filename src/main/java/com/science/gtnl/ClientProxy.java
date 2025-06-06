package com.science.gtnl;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.science.gtnl.Utils.SubscribeEventClientUtils;
import com.science.gtnl.common.block.blocks.ItemBlockEternalGregTechWorkshopRender;
import com.science.gtnl.common.block.blocks.ItemBlockNanoPhagocytosisPlantRender;
import com.science.gtnl.common.block.blocks.tile.TileEntityArtificialStar;
import com.science.gtnl.common.block.blocks.tile.TileEntityEternalGregTechWorkshop;
import com.science.gtnl.common.block.blocks.tile.TileEntityLaserBeacon;
import com.science.gtnl.common.block.blocks.tile.TileEntityNanoPhagocytosisPlant;
import com.science.gtnl.common.block.blocks.tile.TileEntityPlayerDoll;
import com.science.gtnl.common.item.BasicItems;
import com.science.gtnl.common.render.item.ItemBlockArtificialStarRender;
import com.science.gtnl.common.render.item.ItemMeteorMinerMachineRender;
import com.science.gtnl.common.render.item.ItemPlayerDollRenderer;
import com.science.gtnl.common.render.item.ItemTwilightSwordRender;
import com.science.gtnl.common.render.tile.MeteorMinerMachineRender;
import com.science.gtnl.common.render.tile.MeteorMinerRenderer;
import com.science.gtnl.common.render.tile.PlayerDollRenderer;
import com.science.gtnl.common.render.tile.RealArtificialStarRender;
import com.science.gtnl.common.render.tile.RenderEternalGregTechWorkshop;
import com.science.gtnl.common.render.tile.RenderNanoPhagocytosisPlant;
import com.science.gtnl.config.MainConfig;
import com.science.gtnl.loader.BlockLoader;
import com.science.gtnl.loader.ItemLoader;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fox.spiteful.avaritia.render.FancyHaloRenderer;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.BaseMetaTileEntity;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (MainConfig.enableDebugMode) {
            ClientRegistry.bindTileEntitySpecialRenderer(BaseMetaTileEntity.class, new MeteorMinerMachineRender());
            MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(GregTechAPI.sBlockMachines),
                new ItemMeteorMinerMachineRender());
        }

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserBeacon.class, new MeteorMinerRenderer());

        new PlayerDollRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerDoll.class, new PlayerDollRenderer());
        MinecraftForgeClient
            .registerItemRenderer(Item.getItemFromBlock(BlockLoader.PlayerDoll), new ItemPlayerDollRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArtificialStar.class, new RealArtificialStarRender());
        MinecraftForgeClient.registerItemRenderer(
            Item.getItemFromBlock(BlockLoader.BlockArtificialStarRender),
            new ItemBlockArtificialStarRender());

        MinecraftForgeClient.registerItemRenderer(ItemLoader.TwilightSword, new ItemTwilightSwordRender());

        MinecraftForgeClient.registerItemRenderer(
            Item.getItemFromBlock(BlockLoader.BlockNanoPhagocytosisPlantRender),
            new ItemBlockNanoPhagocytosisPlantRender(BlockLoader.BlockNanoPhagocytosisPlantRender));
        ClientRegistry
            .bindTileEntitySpecialRenderer(TileEntityNanoPhagocytosisPlant.class, new RenderNanoPhagocytosisPlant());

        MinecraftForgeClient.registerItemRenderer(
            Item.getItemFromBlock(BlockLoader.BlockEternalGregTechWorkshopRender),
            new ItemBlockEternalGregTechWorkshopRender(BlockLoader.BlockEternalGregTechWorkshopRender));
        ClientRegistry.bindTileEntitySpecialRenderer(
            TileEntityEternalGregTechWorkshop.class,
            new RenderEternalGregTechWorkshop());

        MinecraftForgeClient.registerItemRenderer(ItemLoader.TestItem, new FancyHaloRenderer());
        MinecraftForgeClient.registerItemRenderer(BasicItems.MetaItem, new FancyHaloRenderer());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SubscribeEventClientUtils());
        FMLCommonHandler.instance()
            .bus()
            .register(new SubscribeEventClientUtils());
        super.preInit(event);
    }

}
