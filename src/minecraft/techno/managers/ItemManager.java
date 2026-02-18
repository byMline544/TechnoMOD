package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.items.ItemDust;
import techno.items.ItemIngot;

/**
 * Регистрация предметов мода.
 */
public final class ItemManager {
    public static ItemIngot ingotBronze;
    public static ItemIngot ingotTin;
    public static ItemDust dustBronze;
    public static ItemDust dustTin;

    private ItemManager() {}

    public static void init() {
        ingotBronze = new ItemIngot(5000, "ingotBronze");
        ingotTin = new ItemIngot(5001, "ingotTin");
        dustBronze = new ItemDust(5002, "dustBronze");
        dustTin = new ItemDust(5003, "dustTin");

        GameRegistry.registerItem(ingotBronze, "ingotBronze");
        GameRegistry.registerItem(ingotTin, "ingotTin");
        GameRegistry.registerItem(dustBronze, "dustBronze");
        GameRegistry.registerItem(dustTin, "dustTin");
    }
    public static void registerNames() {
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotBronze, "Бронзовый слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotTin, "Оловянный слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustBronze, "Бронзовая пыль");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustTin, "Оловянная пыль");
    }

}
