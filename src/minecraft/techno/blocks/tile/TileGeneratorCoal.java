package techno.blocks.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Tile угольного генератора.
 * Сжигает любое топливо печи и производит энергию.
 */
public class TileGeneratorCoal extends BaseTileGenerator {
    public int burnTime;

    public TileGeneratorCoal() { super(40000); }

    @Override
    public void updateEntity() {
        if (burnTime > 0) {
            burnTime--;
            setStoredEnergy(getStoredEnergy() + 20);
        }
    }

    public boolean canUseFuel(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack) > 0;
    }
}
