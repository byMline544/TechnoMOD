package techno.blocks.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergyNode;
import techno.api.energy.IEnergySink;
import techno.api.energy.IEnergySource;

/**
 * Тайл кабеля: буферизует и передает энергию соседям.
 */
public class TileCable extends TileEntity implements IEnergyNode, IEnergySource, IEnergySink {
    private int energy;
    private static final int MAX = 2048;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = worldObj.getBlockTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (te instanceof IEnergySource && !(te instanceof TileCable)) {
                IEnergySource src = (IEnergySource) te;
                int got = src.extractEnergy(dir.getOpposite(), Math.min(64, MAX - energy), false);
                energy += got;
            }
        }

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (energy <= 0) break;
            TileEntity te = worldObj.getBlockTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (te instanceof IEnergySink && te != this) {
                IEnergySink sink = (IEnergySink) te;
                int sent = sink.receiveEnergy(dir.getOpposite(), Math.min(64, energy), false);
                energy -= sent;
            }
        }
    }

    @Override public int getStoredEnergy() { return energy; }
    @Override public int getMaxEnergy() { return MAX; }
    @Override public void setStoredEnergy(int amount) { energy = Math.max(0, Math.min(MAX, amount)); }
    @Override public boolean canConnectEnergy(ForgeDirection side) { return true; }

    @Override
    public int extractEnergy(ForgeDirection side, int amount, boolean simulate) {
        int ext = Math.min(energy, amount);
        if (!simulate) energy -= ext;
        return ext;
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        int acc = Math.min(MAX - energy, amount);
        if (!simulate) energy += acc;
        return acc;
    }
}
