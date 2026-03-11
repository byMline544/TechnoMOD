package techno.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import techno.player.ExtendedPlayer;

/**
 * Общие события мода: первичная инициализация данных игрока и приветствие при первом входе.
 */
public class EventHandler {
    @ForgeSubscribe
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getExtendedProperties(ExtendedPlayer.KEY) == null) {
                ExtendedPlayer.register(player);
            }
        }
    }

    @ForgeSubscribe
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (!event.world.isRemote && event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            player.addChatMessage("[TechnoMod] Добро пожаловать в технологический мир!");
        }
    }
}
