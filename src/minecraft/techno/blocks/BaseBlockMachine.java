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
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import techno.TechnoMod;

/**
 * Базовый класс машины для Forge 1.5.2.
 *
 * Текстура машины хранится в одном файле block<Machine>.png.
 * В этом файле лежат 12 сегментов (6 неактивных + 6 активных),
 * но в версии 1.5.2 через стандартный IconRegister нельзя напрямую выбрать
 * произвольный сегмент из одного icon-ресурса без отдельного кастомного рендера.
 * Поэтому на данном этапе подключается единый atlas-икон, а детализация сегментов
 * будет выводиться отдельным block renderer-классом на следующем шаге.
 */
public abstract class BaseBlockMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
    protected Icon atlasIcon;

    private final String atlasTextureName;

    protected BaseBlockMachine(int id, String unlocalizedName, String atlasTextureName) {
        super(id, Material.iron);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        setHardness(3.0F);
        setResistance(8.0F);
        setUnlocalizedName(unlocalizedName);
        this.atlasTextureName = atlasTextureName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        atlasIcon = reg.registerIcon("technomod:" + atlasTextureName);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return side;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving placer, ItemStack itemStack) {
        int yaw = (int) Math.floor((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int front = yaw == 0 ? ForgeDirection.NORTH.ordinal() : yaw == 1 ? ForgeDirection.EAST.ordinal() : yaw == 2 ? ForgeDirection.SOUTH.ordinal() : ForgeDirection.WEST.ordinal();
        world.setBlockMetadataWithNotify(x, y, z, front, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return atlasIcon;
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
