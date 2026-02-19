package techno.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.packet.Packet250CustomPayload;
import techno.TechnoMod;

/**
 * Реестр пакетов: связывает id с обработчиком.
 */
public final class PacketRegistry {
    private static final Map<Integer, IPacketProcessor> REGISTRY = new HashMap<Integer, IPacketProcessor>();

    public static final int ID_SYNC_ENERGY = 1;

    static {
        REGISTRY.put(ID_SYNC_ENERGY, new SyncEnergyPacket());
    }

    private PacketRegistry() {}

    public static void init() {}

    public static void handle(Packet250CustomPayload packet, Player player) {
        if (packet.data == null || packet.data.length == 0) return;
        int id = packet.data[0];
        IPacketProcessor processor = REGISTRY.get(id);
        if (processor != null) processor.handle(packet, player);
    }

    /**
     * Сборка пакета синхронизации энергии для tile.
     */
    public static Packet250CustomPayload makeSyncEnergyPacket(int x, int y, int z, int energy, int max) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);
            out.writeByte(ID_SYNC_ENERGY);
            out.writeInt(x);
            out.writeInt(y);
            out.writeInt(z);
            out.writeInt(energy);
            out.writeInt(max);
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = TechnoMod.CHANNEL;
            packet.data = bos.toByteArray();
            packet.length = packet.data.length;
            packet.isChunkDataPacket = false;
            return packet;
        } catch (Exception e) {
            return null;
        }
    }
}
