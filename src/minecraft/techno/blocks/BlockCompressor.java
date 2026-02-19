package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileCompressor;

/**
 * Компрессор.
 */
public class BlockCompressor extends BaseBlockMachine {
    public BlockCompressor(int id) { super(id, "blockCompressor", "blockCompressor"); }
    @Override protected int getGuiId() { return techno.managers.GuiContainerManager.GUI_COMPRESSOR; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileCompressor(); }
}
