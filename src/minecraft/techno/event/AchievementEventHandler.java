package techno.event;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

/**
 * Обработчик событий достижений.
 */
public class AchievementEventHandler {
    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {
        // Резерв: выдача достижений за первые технологические ресурсы.
    }
}
