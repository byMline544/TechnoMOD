package techno.packets;

import cpw.mods.fml.common.network.Player;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Пакет синхронизации энергии (заглушка для расширения).
 */
public class SyncEnergyPacket implements IPacketProcessor {
    @Override
    public void handle(Packet250CustomPayload packet, Player player) {
        // На данном этапе логика не требуется.
    }
}
