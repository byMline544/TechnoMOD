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
import techno.blocks.tile.BaseTileMachine;

/**
 * Базовый класс машин с раздельными текстурами состояний.
 *
 * Именование текстур (по требованию):
 * - <name>_bottom  -> задняя сторона
 * - <name>_front   -> лицевая сторона
 * - <name>_top     -> нижняя сторона
 * - <name>_side    -> остальные стороны
 * Для активного состояния: <name>_active_<suffix>.
 */
public abstract class BaseBlockMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
    protected Icon idleBottom;
    @SideOnly(Side.CLIENT)
    protected Icon idleFront;
    @SideOnly(Side.CLIENT)
    protected Icon idleTop;
    @SideOnly(Side.CLIENT)
    protected Icon idleSide;

    @SideOnly(Side.CLIENT)
    protected Icon activeBottom;
    @SideOnly(Side.CLIENT)
    protected Icon activeFront;
    @SideOnly(Side.CLIENT)
    protected Icon activeTop;
    @SideOnly(Side.CLIENT)
    protected Icon activeSide;

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
        idleBottom = reg.registerIcon("technomod:" + textureName + "_bottom");
        idleFront = reg.registerIcon("technomod:" + textureName + "_front");
        idleTop = reg.registerIcon("technomod:" + textureName + "_top");
        idleSide = reg.registerIcon("technomod:" + textureName + "_side");

        activeBottom = reg.registerIcon("technomod:" + textureName + "_active_bottom");
        activeFront = reg.registerIcon("technomod:" + textureName + "_active_front");
        activeTop = reg.registerIcon("technomod:" + textureName + "_active_top");
        activeSide = reg.registerIcon("technomod:" + textureName + "_active_side");
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
        return pick(side, facing, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        int facing = world.getBlockMetadata(x, y, z) & 7;
        boolean active = te instanceof BaseTileMachine && ((BaseTileMachine) te).isActive();
        return pick(side, facing, active);
    }

    @SideOnly(Side.CLIENT)
    private Icon pick(int side, int facing, boolean active) {
        Icon texBottom = active ? activeBottom : idleBottom;
        Icon texFront = active ? activeFront : idleFront;
        Icon texTop = active ? activeTop : idleTop;
        Icon texSide = active ? activeSide : idleSide;

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
