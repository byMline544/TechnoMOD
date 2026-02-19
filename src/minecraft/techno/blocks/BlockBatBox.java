package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileBatBox;

/**
 * Блок накопителя энергии BatBox.
 */
public class BlockBatBox extends BaseBlockMachine {
    public BlockBatBox(int id) { super(id, "blockBatBox", "blockBatBox"); }
    @Override protected int getGuiId() { return techno.managers.GuiContainerManager.GUI_BATBOX; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileBatBox(); }
}
