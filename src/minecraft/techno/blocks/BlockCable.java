package techno.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.TechnoMod;
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
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon("technomod:blockCable");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCable();
    }
}
