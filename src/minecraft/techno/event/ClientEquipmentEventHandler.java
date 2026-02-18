package techno.event;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * Клиентский обработчик отображения оверлеев экипировки.
 */
public class ClientEquipmentEventHandler {
    @ForgeSubscribe
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        // Резерв: HUD энергии экипировки.
    }
}
