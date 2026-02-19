package techno.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import techno.TechnoMod;
import techno.api.energy.IEnergyNode;
import techno.blocks.tile.TileCable;

/**
 * Энергокабель для транспортировки te между машинами.
 */
public class BlockCable extends BlockContainer {
    public BlockCable(int id) {
        super(id, Material.circuits);
        setUnlocalizedName("blockCable");
        setHardness(0.8F);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        float min = 0.3125F;
        float max = 0.6875F;
        setBlockBounds(min, min, min, max, max, max);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon("technomod:blockCable");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }



    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float min = 0.3125F;
        float max = 0.6875F;

        boolean down = isConnectable(world, x, y - 1, z, ForgeDirection.UP);
        boolean up = isConnectable(world, x, y + 1, z, ForgeDirection.DOWN);
        boolean north = isConnectable(world, x, y, z - 1, ForgeDirection.SOUTH);
        boolean south = isConnectable(world, x, y, z + 1, ForgeDirection.NORTH);
        boolean west = isConnectable(world, x - 1, y, z, ForgeDirection.EAST);
        boolean east = isConnectable(world, x + 1, y, z, ForgeDirection.WEST);

        float x1 = west ? 0.0F : min;
        float x2 = east ? 1.0F : max;
        float y1 = down ? 0.0F : min;
        float y2 = up ? 1.0F : max;
        float z1 = north ? 0.0F : min;
        float z2 = south ? 1.0F : max;

        setBlockBounds(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCable();
    }

    private boolean isConnectable(IBlockAccess world, int x, int y, int z, ForgeDirection sideToNeighbor) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        return tile instanceof IEnergyNode && ((IEnergyNode) tile).canConnectEnergy(sideToNeighbor);
    }
}
