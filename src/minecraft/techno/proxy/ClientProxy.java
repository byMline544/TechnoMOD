package techno.proxy;

import net.minecraftforge.common.MinecraftForge;
import techno.event.ClientEquipmentEventHandler;

/**
 * Клиентский прокси: регистрация рендеров.
 */
public class ClientProxy extends ServerProxy {
    @Override
    public void registerRenderers() {
        MinecraftForge.EVENT_BUS.register(new ClientEquipmentEventHandler());
    }
}
