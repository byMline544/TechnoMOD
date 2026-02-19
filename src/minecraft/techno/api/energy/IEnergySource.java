package techno.api.energy;

import net.minecraftforge.common.ForgeDirection;

/**
 * Источник энергии: отдает энергию в сеть.
 */
public interface IEnergySource extends IEnergyNode {
    int extractEnergy(ForgeDirection side, int amount, boolean simulate);
}
