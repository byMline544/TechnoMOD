package techno.blocks.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Входной слот.
 */
public class InputSlot extends Slot {
    public InputSlot(IInventory inv, int index, int x, int y) { super(inv, index, x, y); }
}
