package techno.blocks.tile;

import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySource;
import techno.api.network.GlobalEnergyNetworkRegistry;

/**
 * База tile генераторов.
 */
public abstract class BaseTileGenerator extends BaseTileMachine implements IEnergySource {
    protected BaseTileGenerator(int maxEnergy) { super(maxEnergy, 1); }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).tick(worldObj.getWorldTime());
        }
    }

    @Override
    public int extractEnergy(ForgeDirection side, int amount, boolean simulate) {
        int extracted = Math.min(energy, amount);
        if (!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection side) { return true; }

    @Override
    public void validate() {
        super.validate();
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).addSource(this);
        }
    }

    @Override
    public void invalidate() {
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).removeSource(this);
        }
        super.invalidate();
    }
}
