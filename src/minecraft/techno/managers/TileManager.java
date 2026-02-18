package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.blocks.tile.TileElectricFurnace;
import techno.blocks.tile.TileGeneratorCoal;
import techno.blocks.tile.TileSolarPanel;
import techno.blocks.tile.TileCable;

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
    }
}
