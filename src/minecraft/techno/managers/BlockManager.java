package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.blocks.BlockElectricFurnace;
import techno.blocks.BlockGeneratorCoal;
import techno.blocks.BlockSolarPanel;
import techno.blocks.ore.BlockOreBronze;
import techno.blocks.ore.BlockOreTin;

/**
 * Регистрация блоков мода.
 */
public final class BlockManager {
    public static BlockGeneratorCoal generatorCoal;
    public static BlockSolarPanel solarPanel;
    public static BlockElectricFurnace electricFurnace;
    public static BlockOreBronze oreBronze;
    public static BlockOreTin oreTin;

    private BlockManager() {}

    public static void init() {
        generatorCoal = new BlockGeneratorCoal(2300);
        solarPanel = new BlockSolarPanel(2301);
        electricFurnace = new BlockElectricFurnace(2302);
        oreBronze = new BlockOreBronze(2303);
        oreTin = new BlockOreTin(2304);

        GameRegistry.registerBlock(generatorCoal, "generatorCoal");
        GameRegistry.registerBlock(solarPanel, "solarPanel");
        GameRegistry.registerBlock(electricFurnace, "electricFurnace");
        GameRegistry.registerBlock(oreBronze, "oreBronze");
        GameRegistry.registerBlock(oreTin, "oreTin");
    }
}
