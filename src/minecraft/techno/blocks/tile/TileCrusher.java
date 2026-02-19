package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import techno.api.energy.IEnergySink;
import techno.api.network.GlobalEnergyNetworkRegistry;
import techno.storage.crafting.crusher.CrusherRecipes;

/**
 * Tile дробителя.
 */
public class TileCrusher extends BaseTileMachine implements IEnergySink {
    public int progress;

    public TileCrusher() { super(24000, 2); }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            boolean working = canProcess() && energy >= 20;
            if (working) {
                progress++;
                energy -= 20;
                if (progress >= 120) {
                    progress = 0;
                    processItem();
                }
            } else if (!canProcess()) {
                progress = 0;
            }
            setActiveState(working);
        }
    }

    private boolean canProcess() {
        if (inventory[0] == null) return false;
        ItemStack result = CrusherRecipes.getResult(inventory[0]);
        if (result == null) return false;
        if (inventory[1] == null) return true;
        if (!inventory[1].isItemEqual(result)) return false;
        return inventory[1].stackSize + result.stackSize <= getInventoryStackLimit();
    }

    private void processItem() {
        if (!canProcess()) return;
        ItemStack result = CrusherRecipes.getResult(inventory[0]).copy();
        if (inventory[1] == null) inventory[1] = result;
        else inventory[1].stackSize += result.stackSize;
        inventory[0].stackSize--;
        if (inventory[0].stackSize <= 0) inventory[0] = null;
    }

    @Override public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) { int a=Math.min(maxEnergy-energy,amount); if(!simulate) energy+=a; return a; }
    @Override public boolean canConnectEnergy(ForgeDirection side) { return true; }
    @Override public void validate() { super.validate(); if(worldObj!=null&&!worldObj.isRemote) GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).addSink(this); }
    @Override public void invalidate() { if(worldObj!=null&&!worldObj.isRemote) GlobalEnergyNetworkRegistry.get(worldObj.provider.dimensionId).removeSink(this); super.invalidate(); }
    @Override protected String getInventoryName() { return "crusher"; }
    @Override public boolean isStackValidForSlot(int i, ItemStack stack) { return i==0; }
}
