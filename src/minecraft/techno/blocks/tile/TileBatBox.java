package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySink;
import techno.api.energy.IEnergySource;
import techno.api.network.GlobalEnergyNetworkRegistry;

/**
 * BatBox: принимает и отдаёт энергию как буфер сети.
 */
public class TileBatBox extends BaseTileMachine implements IEnergySink, IEnergySource {
    public TileBatBox() {
        super(300000, 0);
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            techno.api.network.GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).tick(worldObj.getWorldTime());
            setActiveState(energy > 0);
        }
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        int accepted = Math.min(128, Math.min(maxEnergy - energy, amount));
        if (!simulate) energy += accepted;
        return accepted;
    }

    @Override
    public int extractEnergy(ForgeDirection side, int amount, boolean simulate) {
        int extracted = Math.min(128, Math.min(energy, amount));
        if (!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection side) { return true; }

    @Override
    public void validate() {
        super.validate();
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).addSink(this);
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).addSource(this);
        }
    }

    @Override
    public void invalidate() {
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).removeSink(this);
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).removeSource(this);
        }
        super.invalidate();
    }

    @Override public int getEnergyPriority() { return 80; }

    @Override protected String getInventoryName() { return "batbox"; }
    @Override public boolean isStackValidForSlot(int i, ItemStack stack) { return false; }
}
