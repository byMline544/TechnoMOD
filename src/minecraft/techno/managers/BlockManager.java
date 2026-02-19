package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.blocks.BlockElectricFurnace;
import techno.blocks.BlockGeneratorCoal;
import techno.blocks.BlockSolarPanel;
import techno.blocks.BlockCable;
import techno.blocks.BlockCrusher;
import techno.blocks.BlockCompressor;
import techno.blocks.ore.BlockOreBronze;
import techno.blocks.ore.BlockOreTin;
import techno.blocks.ore.BlockOreCopper;

/**
 * Регистрация блоков мода.
 */
public final class BlockManager {
    public static BlockGeneratorCoal generatorCoal;
    public static BlockSolarPanel solarPanel;
    public static BlockElectricFurnace electricFurnace;
    public static BlockOreBronze oreBronze;
    public static BlockOreTin oreTin;
    public static BlockOreCopper oreCopper;
    public static BlockCable cable;
    public static BlockCrusher crusher;
    public static BlockCompressor compressor;

    private BlockManager() {}

    public static void init() {
        generatorCoal = new BlockGeneratorCoal(2300);
        solarPanel = new BlockSolarPanel(2301);
        electricFurnace = new BlockElectricFurnace(2302);
        oreBronze = new BlockOreBronze(2303);
        oreTin = new BlockOreTin(2304);
        oreCopper = new BlockOreCopper(2308);
        cable = new BlockCable(2305);
        crusher = new BlockCrusher(2306);
        compressor = new BlockCompressor(2307);

        GameRegistry.registerBlock(generatorCoal, "generatorCoal");
        GameRegistry.registerBlock(solarPanel, "solarPanel");
        GameRegistry.registerBlock(electricFurnace, "electricFurnace");
        GameRegistry.registerBlock(oreBronze, "oreBronze");
        GameRegistry.registerBlock(oreTin, "oreTin");
        GameRegistry.registerBlock(oreCopper, "oreCopper");
        GameRegistry.registerBlock(cable, "cable");
        GameRegistry.registerBlock(crusher, "crusher");
        GameRegistry.registerBlock(compressor, "compressor");
    }
    public static void registerNames() {
        cpw.mods.fml.common.registry.LanguageRegistry.addName(generatorCoal, "Угольный генератор");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(solarPanel, "Солнечная панель");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(electricFurnace, "Электропечь");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(oreBronze, "Бронзовая руда");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(oreTin, "Оловянная руда");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(oreCopper, "Медная руда");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(cable, "Медный кабель");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(crusher, "Дробитель");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(compressor, "Компрессор");
    }

}
