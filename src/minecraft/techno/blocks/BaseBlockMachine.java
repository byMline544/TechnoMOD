package techno.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import techno.TechnoMod;

/**
 * Базовый класс машины с 12-сегментной текстурой:
 * 6 граней для неактивного состояния + 6 для активного.
 */
public abstract class BaseBlockMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
    protected Icon[] idle = new Icon[6];
    @SideOnly(Side.CLIENT)
    protected Icon[] active = new Icon[6];

    protected BaseBlockMachine(int id, String texture) {
        super(id, Material.iron);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        setHardness(3.0F);
        setResistance(8.0F);
        setUnlocalizedName(texture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        for (int i = 0; i < 6; i++) {
            idle[i] = reg.registerIcon("technomod:blocks/" + getTextureBaseName() + "_idle_" + i);
            active[i] = reg.registerIcon("technomod:blocks/" + getTextureBaseName() + "_active_" + i);
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return side;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, net.minecraft.item.ItemStack itemStack) {
        int yaw = (int)Math.floor((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int front = yaw == 0 ? ForgeDirection.NORTH.ordinal() : yaw == 1 ? ForgeDirection.EAST.ordinal() : yaw == 2 ? ForgeDirection.SOUTH.ordinal() : ForgeDirection.WEST.ordinal();
        world.setBlockMetadataWithNotify(x, y, z, front, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        boolean isActive = meta >= 8;
        int front = isActive ? meta - 8 : meta;
        if (side == front) {
            return isActive ? active[3] : idle[3];
        }
        return isActive ? active[side] : idle[side];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        if (!world.isRemote) {
            player.openGui(TechnoMod.instance, getGuiId(), world, x, y, z);
        }
        return true;
    }

    protected abstract int getGuiId();
    protected abstract String getTextureBaseName();
    @Override public abstract TileEntity createNewTileEntity(World world);
}
