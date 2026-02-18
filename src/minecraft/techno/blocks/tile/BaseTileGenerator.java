package techno.blocks.tile;

import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySource;

/**
 * База tile генераторов.
 */
public abstract class BaseTileGenerator extends BaseTileMachine implements IEnergySource {
    protected BaseTileGenerator(int maxEnergy) { super(maxEnergy); }

    @Override
    public int extractEnergy(ForgeDirection side, int amount, boolean simulate) {
        int extracted = Math.min(energy, amount);
        if (!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection side) { return true; }
}
