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
 * Базовый класс машины с 12-сегментной текстурой (6 idle + 6 active).
 */
public abstract class BaseBlockMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
    protected Icon[] textures;

    private final String textureName;

    /**
     * Таблица пересчета стороны+направления в индекс спрайта.
     */
    public static final int[][] sideAndFacingToSpriteOffset = new int[][]{{3, 2, 0, 0, 0, 0}, {2, 3, 1, 1, 1, 1}, {1, 1, 3, 2, 5, 4}, {0, 0, 2, 3, 4, 5}, {4, 5, 4, 5, 3, 2}, {5, 4, 5, 4, 2, 3}};

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
        textures = new Icon[12];
        for (int i = 0; i < 12; i++) {
            textures[i] = reg.registerIcon("technomod:" + textureName + "." + i);
        }
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
        int sub = sideAndFacingToSpriteOffset[side][facing % 6];
        return textures[sub];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        int facing = meta & 7;
        boolean active = te instanceof BaseTileMachine && ((BaseTileMachine) te).isActive();
        int sub = sideAndFacingToSpriteOffset[side][facing % 6] + (active ? 6 : 0);
        return textures[sub];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        if (!world.isRemote) {
            player.openGui(TechnoMod.instance, getGuiId(), world, x, y, z);
        }
        return true;
    }

    protected abstract int getGuiId();

    @Override
    public abstract TileEntity createNewTileEntity(World world);
}
