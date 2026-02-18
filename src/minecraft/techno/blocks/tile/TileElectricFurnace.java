package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySink;
import techno.api.network.GlobalEnergyNetworkRegistry;
import techno.storage.crafting.electricfurnace.ElectricFurnaceRecipes;

/**
 * Tile электропечи.
 * Слоты: 0 вход, 1 выход.
 */
public class TileElectricFurnace extends BaseTileMachine implements IEnergySink {
    public int progress;

    public TileElectricFurnace() { super(24000, 2); }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote && canSmelt() && energy >= 16) {
            progress++;
            energy -= 16;
            if (progress >= 100) {
                progress = 0;
                smeltItem();
            }
        } else if (!canSmelt()) {
            progress = 0;
        }
    }

    private boolean canSmelt() {
        if (inventory[0] == null) return false;
        ItemStack result = ElectricFurnaceRecipes.getResult(inventory[0]);
        if (result == null) return false;
        if (inventory[1] == null) return true;
        if (!inventory[1].isItemEqual(result)) return false;
        return inventory[1].stackSize < getInventoryStackLimit();
    }

    private void smeltItem() {
        if (!canSmelt()) return;
        ItemStack result = ElectricFurnaceRecipes.getResult(inventory[0]).copy();
        if (inventory[1] == null) {
            inventory[1] = result;
        } else {
            inventory[1].stackSize += result.stackSize;
        }
        inventory[0].stackSize--;
        if (inventory[0].stackSize <= 0) inventory[0] = null;
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        int accepted = Math.min(maxEnergy - energy, amount);
        if (!simulate) energy += accepted;
        return accepted;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection side) { return true; }

    @Override
    public void validate() {
        super.validate();
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).addSink(this);
        }
    }

    @Override
    public void invalidate() {
        if (worldObj != null && !worldObj.isRemote) {
            GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).removeSink(this);
        }
        super.invalidate();
    }

    @Override
    protected String getInventoryName() { return "electric.furnace"; }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack stack) {
        return i == 0;
    }
}
