package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySink;
import techno.storage.crafting.electricfurnace.ElectricFurnaceRecipes;

/**
 * Tile электропечи.
 */
public class TileElectricFurnace extends BaseTileMachine implements IEnergySink {
    public int progress;

    public TileElectricFurnace() { super(24000); }

    @Override
    public void updateEntity() {
        if (energy >= 16) {
            progress++;
            energy -= 16;
            if (progress >= 100) {
                progress = 0;
                // Логика инвентаря на следующем этапе.
            }
        } else {
            progress = 0;
        }
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        int accepted = Math.min(maxEnergy - energy, amount);
        if (!simulate) energy += accepted;
        return accepted;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection side) { return true; }
}
