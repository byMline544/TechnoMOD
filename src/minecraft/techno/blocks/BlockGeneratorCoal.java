package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileGeneratorCoal;

/**
 * Угольный генератор на любом печном топливе.
 */
public class BlockGeneratorCoal extends BaseBlockGenerator {
    public BlockGeneratorCoal(int id) { super(id, "blockGeneratorCoal"); }
    @Override protected int getGuiId() { return 1; }
    @Override protected String getTextureBaseName() { return "blockGeneratorCoal"; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileGeneratorCoal(); }
}
