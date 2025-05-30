package com.science.gtnl.common.block.blocks;

import static com.science.gtnl.ScienceNotLeisure.RESOURCE_ROOT_ID;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.science.gtnl.Utils.enums.GTNLItemList;
import com.science.gtnl.client.GTNLCreativeTabs;
import com.science.gtnl.common.block.blocks.tile.TileEntityLaserBeacon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Textures;
import gregtech.api.util.GTLanguageManager;

public class BlockLaserBeacon extends Block implements ITileEntityProvider {

    protected IIcon topBlockIcon;
    public static IIcon[] textures;

    public BlockLaserBeacon() {
        super(Material.iron);
        setBlockName("LaserBeacon");
        this.setCreativeTab(GTNLCreativeTabs.GTNotLeisureBlock);
        GTLanguageManager.addStringLocalization(getUnlocalizedName() + ".name", "Laser Inducing Beacon");
        GregTechAPI.registerMachineBlock(this, -1);
        GameRegistry.registerBlock(this, ItemBlockLaserBeacon.class, getUnlocalizedName());
        GTNLItemList.LaserBeacon.set(new ItemStack(this, 1));
    }

    @Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GregTechAPI.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTechAPI.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData) {
        if (GregTechAPI.isMachineBlock(this, aMetaData)) {
            GregTechAPI.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public String getHarvestTool(int aMeta) {
        return "wrench";
    }

    @Override
    public int getHarvestLevel(int aMeta) {
        return 2;
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.LaserBeacon";
    }

    private static final String TEXTURE_NAME_OVERLAY_ACTIVE = RESOURCE_ROOT_ID + ":" + "iconsets/LASER_BEACON";

    public static Textures.BlockIcons.CustomIcon LASER_BEACON = new Textures.BlockIcons.CustomIcon(
        TEXTURE_NAME_OVERLAY_ACTIVE);

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = Textures.BlockIcons.MACHINE_COIL_SUPERCONDUCTOR.getIcon();
        topBlockIcon = LASER_BEACON.getIcon();
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? topBlockIcon : blockIcon;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        return 1.0f;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserBeacon();
    }
}
