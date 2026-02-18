package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Tile угольного генератора.
 */
public class TileGeneratorCoal extends BaseTileGenerator {
    public int burnTime;

    public TileGeneratorCoal() { super(40000); }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (burnTime <= 0 && inventory[0] != null && canUseFuel(inventory[0])) {
                burnTime = TileEntityFurnace.getItemBurnTime(inventory[0]);
                inventory[0].stackSize--;
                if (inventory[0].stackSize <= 0) inventory[0] = null;
            }
            if (burnTime > 0) {
                burnTime--;
                setStoredEnergy(getStoredEnergy() + 20);
                setActiveState(true);
            } else {
                setActiveState(false);
            }
        }
    }

    public boolean canUseFuel(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack) > 0;
    }

    @Override protected String getInventoryName() { return "generator.coal"; }
    @Override public boolean isStackValidForSlot(int i, ItemStack stack) { return i == 0 && canUseFuel(stack); }
}
