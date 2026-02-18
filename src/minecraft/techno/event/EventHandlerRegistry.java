package techno.event;

import net.minecraftforge.common.MinecraftForge;

/**
 * Централизованная регистрация всех обработчиков событий.
 */
public final class EventHandlerRegistry {
    private EventHandlerRegistry() {}

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerCombatEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEquipmentEventHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEquipmentEventHandler());
        MinecraftForge.EVENT_BUS.register(new AchievementEventHandler());
    }
}
