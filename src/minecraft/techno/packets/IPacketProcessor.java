package techno.packets;

import cpw.mods.fml.common.network.Player;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Интерфейс обработчика пакета.
 */
public interface IPacketProcessor {
    void handle(Packet250CustomPayload packet, Player player);
}
