package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileSolarPanel;

/**
 * Солнечная панель с дневной генерацией te/t.
 */
public class BlockSolarPanel extends BaseBlockGenerator {
    public BlockSolarPanel(int id) { super(id, "blockSolarPanel", "blockSolarPanel"); }
    @Override protected int getGuiId() { return techno.managers.GuiContainerManager.GUI_SOLAR; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileSolarPanel(); }
}
