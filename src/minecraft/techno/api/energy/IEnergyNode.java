package techno.api.energy;

import net.minecraftforge.common.ForgeDirection;

/**
 * Базовый узел энергосети TE.
 */
public interface IEnergyNode {
    int getStoredEnergy();
    int getMaxEnergy();
    void setStoredEnergy(int amount);
    boolean canConnectEnergy(ForgeDirection side);
}
