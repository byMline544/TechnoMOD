package techno.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import techno.blocks.tile.BaseTileMachine;

/**
 * Пакет синхронизации энергии tile-машины.
 * Формат: [id=1][x][y][z][energy][maxEnergy].
 */
public class SyncEnergyPacket implements IPacketProcessor {
    @Override
    public void handle(Packet250CustomPayload packet, Player player) {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.data));
            in.readByte(); // id
            int x = in.readInt();
            int y = in.readInt();
            int z = in.readInt();
            int energy = in.readInt();
            int max = in.readInt();

            if (!(player instanceof EntityPlayer)) return;
            EntityPlayer ep = (EntityPlayer) player;
            TileEntity te = ep.worldObj.getBlockTileEntity(x, y, z);
            if (te instanceof BaseTileMachine) {
                BaseTileMachine machine = (BaseTileMachine) te;
                machine.setStoredEnergy(Math.min(energy, max));
            }
        } catch (Exception ignored) {
            // Защита от битых пакетов.
        }
    }
}
