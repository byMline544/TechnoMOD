package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumArmorMaterial;
import techno.items.ItemComponent;
import techno.items.ItemDust;
import techno.items.ItemIngot;
import techno.items.armor.ItemTechElectricArmor;
import techno.items.book.ItemTechBook;

/**
 * Регистрация предметов мода.
 */
public final class ItemManager {
    public static ItemIngot ingotBronze;
    public static ItemIngot ingotTin;
    public static ItemIngot ingotCopper;
    public static ItemDust dustBronze;
    public static ItemDust dustTin;
    public static ItemDust dustCopper;
    public static ItemIngot ingotUranium;
    public static ItemDust dustUranium;

    public static ItemComponent plateBronze;
    public static ItemComponent circuitBasic;
    public static ItemComponent battery;
    public static ItemComponent cableItem;
    public static ItemComponent energyCrystal;
    public static ItemComponent lapotronCrystal;
    public static ItemComponent machineCasing;
    public static ItemComponent reactorCore;

    public static ItemTechElectricArmor jetpack;
    public static ItemTechElectricArmor nanoHelmet;
    public static ItemTechElectricArmor nanoChest;
    public static ItemTechElectricArmor nanoLegs;
    public static ItemTechElectricArmor nanoBoots;
    public static ItemTechElectricArmor quantumHelmet;
    public static ItemTechElectricArmor quantumChest;
    public static ItemTechElectricArmor quantumLegs;
    public static ItemTechElectricArmor quantumBoots;
    public static ItemTechBook techBook;

    private ItemManager() {}

    public static void init() {
        ingotBronze = new ItemIngot(5000, "ingotBronze");
        ingotTin = new ItemIngot(5001, "ingotTin");
        dustBronze = new ItemDust(5002, "dustBronze");
        dustTin = new ItemDust(5003, "dustTin");
        ingotCopper = new ItemIngot(5004, "ingotCopper");
        dustCopper = new ItemDust(5005, "dustCopper");
        ingotUranium = new ItemIngot(5019, "ingotUranium");
        dustUranium = new ItemDust(5020, "dustUranium");

        plateBronze = new ItemComponent(5006, "plateBronze");
        circuitBasic = new ItemComponent(5007, "circuitBasic");
        battery = new ItemComponent(5008, "battery");
        cableItem = new ItemComponent(5009, "cableItem");
        energyCrystal = new ItemComponent(5021, "energyCrystal");
        lapotronCrystal = new ItemComponent(5022, "lapotronCrystal");
        machineCasing = new ItemComponent(5023, "machineCasing");
        reactorCore = new ItemComponent(5024, "reactorCore");

        jetpack = new ItemTechElectricArmor(5010, EnumArmorMaterial.IRON, 0, 1, "jetpack", 120000);
        nanoHelmet = new ItemTechElectricArmor(5011, EnumArmorMaterial.DIAMOND, 0, 0, "nanoHelmet", 200000);
        nanoChest = new ItemTechElectricArmor(5012, EnumArmorMaterial.DIAMOND, 0, 1, "nanoChest", 300000);
        nanoLegs = new ItemTechElectricArmor(5013, EnumArmorMaterial.DIAMOND, 0, 2, "nanoLegs", 250000);
        nanoBoots = new ItemTechElectricArmor(5014, EnumArmorMaterial.DIAMOND, 0, 3, "nanoBoots", 180000);
        quantumHelmet = new ItemTechElectricArmor(5015, EnumArmorMaterial.DIAMOND, 0, 0, "quantumHelmet", 1000000);
        quantumChest = new ItemTechElectricArmor(5016, EnumArmorMaterial.DIAMOND, 0, 1, "quantumChest", 1500000);
        quantumLegs = new ItemTechElectricArmor(5017, EnumArmorMaterial.DIAMOND, 0, 2, "quantumLegs", 1200000);
        quantumBoots = new ItemTechElectricArmor(5018, EnumArmorMaterial.DIAMOND, 0, 3, "quantumBoots", 1000000);
        techBook = new ItemTechBook(5025, "techBook");

        GameRegistry.registerItem(ingotBronze, "ingotBronze");
        GameRegistry.registerItem(ingotTin, "ingotTin");
        GameRegistry.registerItem(dustBronze, "dustBronze");
        GameRegistry.registerItem(dustTin, "dustTin");
        GameRegistry.registerItem(ingotCopper, "ingotCopper");
        GameRegistry.registerItem(dustCopper, "dustCopper");
        GameRegistry.registerItem(ingotUranium, "ingotUranium");
        GameRegistry.registerItem(dustUranium, "dustUranium");
        GameRegistry.registerItem(plateBronze, "plateBronze");
        GameRegistry.registerItem(circuitBasic, "circuitBasic");
        GameRegistry.registerItem(battery, "battery");
        GameRegistry.registerItem(cableItem, "cableItem");
        GameRegistry.registerItem(energyCrystal, "energyCrystal");
        GameRegistry.registerItem(lapotronCrystal, "lapotronCrystal");
        GameRegistry.registerItem(machineCasing, "machineCasing");
        GameRegistry.registerItem(reactorCore, "reactorCore");
        GameRegistry.registerItem(jetpack, "jetpack");
        GameRegistry.registerItem(nanoHelmet, "nanoHelmet");
        GameRegistry.registerItem(nanoChest, "nanoChest");
        GameRegistry.registerItem(nanoLegs, "nanoLegs");
        GameRegistry.registerItem(nanoBoots, "nanoBoots");
        GameRegistry.registerItem(quantumHelmet, "quantumHelmet");
        GameRegistry.registerItem(quantumChest, "quantumChest");
        GameRegistry.registerItem(quantumLegs, "quantumLegs");
        GameRegistry.registerItem(quantumBoots, "quantumBoots");
        GameRegistry.registerItem(techBook, "techBook");
    }

    public static void registerNames() {
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotBronze, "Бронзовый слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotTin, "Оловянный слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotCopper, "Медный слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustBronze, "Бронзовая пыль");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustTin, "Оловянная пыль");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustCopper, "Медная пыль");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(plateBronze, "Бронзовая пластина");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(circuitBasic, "Базовая микросхема");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(battery, "Батарея");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(cableItem, "Кабель");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(ingotUranium, "Урановый слиток");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(dustUranium, "Урановая пыль");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(energyCrystal, "Энерго-кристалл");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(lapotronCrystal, "Лапотрон-кристалл");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(machineCasing, "Корпус механизма");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(reactorCore, "Реакторный стержень");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(jetpack, "Джетпак");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(nanoHelmet, "Нано шлем");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(nanoChest, "Нано кираса");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(nanoLegs, "Нано поножи");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(nanoBoots, "Нано ботинки");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(quantumHelmet, "Квантовый шлем");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(quantumChest, "Квантовая кираса");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(quantumLegs, "Квантовые поножи");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(quantumBoots, "Квантовые ботинки");
        cpw.mods.fml.common.registry.LanguageRegistry.addName(techBook, "Техно-книга");
    }
}
