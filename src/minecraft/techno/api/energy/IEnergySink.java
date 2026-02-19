package techno.api.energy;

import net.minecraftforge.common.ForgeDirection;

/**
 * Потребитель энергии: принимает энергию из сети.
 */
public interface IEnergySink extends IEnergyNode {
    int receiveEnergy(ForgeDirection side, int amount, boolean simulate);
}
