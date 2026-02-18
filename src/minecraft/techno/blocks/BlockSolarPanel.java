package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileSolarPanel;

/**
 * Солнечная панель с дневной генерацией te/t.
 */
public class BlockSolarPanel extends BaseBlockGenerator {
    public BlockSolarPanel(int id) { super(id, "blockSolarPanel"); }
    @Override protected int getGuiId() { return 2; }
    @Override protected String getTextureBaseName() { return "blockSolarPanel"; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileSolarPanel(); }
}
