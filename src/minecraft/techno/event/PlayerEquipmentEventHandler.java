package techno.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * Серверный обработчик экипировки/взаимодействий игрока.
 */
public class PlayerEquipmentEventHandler {
    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        if (player == null) return;
        // Резерв под энерго-экипировку и спец-инструменты.
    }
}
