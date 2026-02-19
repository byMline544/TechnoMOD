package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.blocks.tile.TileElectricFurnace;
import techno.blocks.tile.TileGeneratorCoal;
import techno.blocks.tile.TileSolarPanel;
import techno.blocks.tile.TileCable;
import techno.blocks.tile.TileCrusher;
import techno.blocks.tile.TileCompressor;

/**
 * Регистрация TileEntity.
 */
public final class TileManager {
    private TileManager() {}

    public static void init() {
        GameRegistry.registerTileEntity(TileGeneratorCoal.class, "TE_GeneratorCoal");
        GameRegistry.registerTileEntity(TileSolarPanel.class, "TE_SolarPanel");
        GameRegistry.registerTileEntity(TileElectricFurnace.class, "TE_ElectricFurnace");
        GameRegistry.registerTileEntity(TileCable.class, "TE_Cable");
        GameRegistry.registerTileEntity(TileCrusher.class, "TE_Crusher");
        GameRegistry.registerTileEntity(TileCompressor.class, "TE_Compressor");
    }
}
