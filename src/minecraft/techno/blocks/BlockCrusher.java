package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileCrusher;

/**
 * Дробитель.
 */
public class BlockCrusher extends BaseBlockMachine {
    public BlockCrusher(int id) { super(id, "blockCrusher", "blockCrusher"); }
    @Override protected int getGuiId() { return techno.managers.GuiContainerManager.GUI_CRUSHER; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileCrusher(); }
}
