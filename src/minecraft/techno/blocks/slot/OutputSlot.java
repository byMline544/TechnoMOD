package techno.blocks.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Слот вывода: запрещает ручную укладку предметов.
 */
public class OutputSlot extends Slot {
    public OutputSlot(IInventory inv, int index, int x, int y) { super(inv, index, x, y); }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
