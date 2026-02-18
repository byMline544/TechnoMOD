package techno.packets;

import java.util.HashMap;
import java.util.Map;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Реестр пакетов: связывает id с обработчиком.
 */
public final class PacketRegistry {
    private static final Map<Integer, IPacketProcessor> REGISTRY = new HashMap<Integer, IPacketProcessor>();

    static {
        REGISTRY.put(1, new SyncEnergyPacket());
    }

    private PacketRegistry() {}

    public static void init() {}

    public static void handle(Packet250CustomPayload packet, Player player) {
        if (packet.data == null || packet.data.length == 0) return;
        int id = packet.data[0];
        IPacketProcessor processor = REGISTRY.get(id);
        if (processor != null) {
            processor.handle(packet, player);
        }
    }
}
