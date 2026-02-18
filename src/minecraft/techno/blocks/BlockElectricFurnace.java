package techno.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techno.blocks.tile.TileElectricFurnace;

/**
 * Электропечь - потребитель энергии сети.
 */
public class BlockElectricFurnace extends BaseBlockMachine {
    public BlockElectricFurnace(int id) { super(id, "blockElectricFurnace", "blockElectricFurnace"); }
    @Override protected int getGuiId() { return 3; }
    @Override public TileEntity createNewTileEntity(World world) { return new TileElectricFurnace(); }
}
