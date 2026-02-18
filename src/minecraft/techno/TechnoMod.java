package techno;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
import techno.event.EventHandlerRegistry;
import techno.managers.AchievementManager;
import techno.managers.BlockManager;
import techno.managers.GuiContainerManager;
import techno.managers.ItemManager;
import techno.managers.RecipeManager;
import techno.managers.RenderManager;
import techno.managers.TileManager;
import techno.managers.WorldManager;
import techno.packets.PacketHandler;
import techno.packets.PacketRegistry;
import techno.proxy.ServerProxy;

/**
 * Главный класс мода.
 * Для Forge 1.5.2 используется жизненный цикл @Mod.PreInit/@Mod.Init/@Mod.PostInit.
 */
@Mod(modid = TechnoMod.MOD_ID, name = TechnoMod.MOD_NAME, version = TechnoMod.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { TechnoMod.CHANNEL }, packetHandler = PacketHandler.class)
public class TechnoMod {
    public static final String MOD_ID = "technomod";
    public static final String MOD_NAME = "TechnoMod";
    public static final String MOD_VERSION = "0.0.1";
    public static final String CHANNEL = "TEmod";

    @Mod.Instance(MOD_ID)
    public static TechnoMod instance;

    @SidedProxy(clientSide = "techno.proxy.ClientProxy", serverSide = "techno.proxy.ServerProxy")
    public static ServerProxy proxy;

    public static final CreativeTechnoTab TAB_BLOCKS = new CreativeTechnoTab("TechnoBlocks", true);
    public static final CreativeTechnoTab TAB_ITEMS = new CreativeTechnoTab("TechnoItems", false);

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ItemManager.init();
        BlockManager.init();
        TileManager.init();
        PacketRegistry.init();
        proxy.registerRenderers();
    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        GuiContainerManager.init();
        WorldManager.init();
        RecipeManager.init();
        RenderManager.init();
        AchievementManager.init();
        EventHandlerRegistry.register();

        LanguageRegistry.addName(BlockManager.generatorCoal, "Угольный генератор");
        LanguageRegistry.addName(BlockManager.solarPanel, "Солнечная панель");
        LanguageRegistry.addName(BlockManager.electricFurnace, "Электропечь");
        LanguageRegistry.addName(BlockManager.oreBronze, "Бронзовая руда");
        LanguageRegistry.addName(BlockManager.oreTin, "Оловянная руда");

        LanguageRegistry.addName(ItemManager.ingotBronze, "Бронзовый слиток");
        LanguageRegistry.addName(ItemManager.ingotTin, "Оловянный слиток");
        LanguageRegistry.addName(ItemManager.dustBronze, "Бронзовая пыль");
        LanguageRegistry.addName(ItemManager.dustTin, "Оловянная пыль");
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Резерв под интеграции с внешними модами и баланс-патчи.
    }
}
