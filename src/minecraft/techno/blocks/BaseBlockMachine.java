package techno.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import techno.TechnoMod;

/**
 * Базовый класс машин с единой суффиксной схемой текстур (без idle/active).
 *
 * Требуемое именование:
 * - <name>_bottom  -> задняя сторона
 * - <name>_front   -> лицевая сторона
 * - <name>_top     -> нижняя сторона
 * - <name>_side    -> остальные стороны
 */
public abstract class BaseBlockMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
    protected Icon texBottom;
    @SideOnly(Side.CLIENT)
    protected Icon texFront;
    @SideOnly(Side.CLIENT)
    protected Icon texTop;
    @SideOnly(Side.CLIENT)
    protected Icon texSide;

    private final String textureName;

    protected BaseBlockMachine(int id, String unlocalizedName, String textureName) {
        super(id, Material.iron);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        setHardness(3.0F);
        setResistance(8.0F);
        setUnlocalizedName(unlocalizedName);
        this.textureName = textureName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        texBottom = reg.registerIcon("technomod:" + textureName + "_bottom");
        texFront = reg.registerIcon("technomod:" + textureName + "_front");
        texTop = reg.registerIcon("technomod:" + textureName + "_top");
        texSide = reg.registerIcon("technomod:" + textureName + "_side");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving placer, ItemStack itemStack) {
        int yaw = (int) Math.floor((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int front = yaw == 0 ? ForgeDirection.NORTH.ordinal() : yaw == 1 ? ForgeDirection.EAST.ordinal() : yaw == 2 ? ForgeDirection.SOUTH.ordinal() : ForgeDirection.WEST.ordinal();
        world.setBlockMetadataWithNotify(x, y, z, front & 7, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        int facing = meta & 7;
        return pick(side, facing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int facing = world.getBlockMetadata(x, y, z) & 7;
        return pick(side, facing);
    }

    @SideOnly(Side.CLIENT)
    private Icon pick(int side, int facing) {
        if (side == facing) return texFront;

        int back = ForgeDirection.getOrientation(facing).getOpposite().ordinal();
        if (side == back) return texBottom;

        if (side == ForgeDirection.DOWN.ordinal()) return texTop;

        return texSide;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        if (!world.isRemote) player.openGui(TechnoMod.instance, getGuiId(), world, x, y, z);
        return true;
    }

    protected abstract int getGuiId();
    @Override public abstract TileEntity createNewTileEntity(World world);
}
