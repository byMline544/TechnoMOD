package techno.blocks.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Кастомный слот UpgradeSlot.
 */
public class UpgradeSlot extends Slot {
    public UpgradeSlot(IInventory inv, int index, int x, int y) { super(inv, index, x, y); }
}
