package techno.blocks.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Кастомный слот OutputSlot.
 */
public class OutputSlot extends Slot {
    public OutputSlot(IInventory inv, int index, int x, int y) { super(inv, index, x, y); }
}
