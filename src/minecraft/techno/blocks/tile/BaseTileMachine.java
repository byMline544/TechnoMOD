package techno.blocks.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import techno.api.energy.IEnergyNode;

/**
 * Базовый tile машин с хранением энергии и NBT.
 */
public abstract class BaseTileMachine extends TileEntity implements IEnergyNode {
    protected int energy;
    protected int maxEnergy;

    protected BaseTileMachine(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    @Override
    public int getStoredEnergy() { return energy; }
    @Override
    public int getMaxEnergy() { return maxEnergy; }
    @Override
    public void setStoredEnergy(int amount) { energy = Math.max(0, Math.min(maxEnergy, amount)); }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        energy = nbt.getInteger("Energy");
        maxEnergy = nbt.getInteger("MaxEnergy");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Energy", energy);
        nbt.setInteger("MaxEnergy", maxEnergy);
    }
}
