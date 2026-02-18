package techno.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Базовый контейнер машины с безопасным shift-click переносом.
 */
public abstract class BaseContainerMachine extends Container {
    protected final IInventory tile;

    protected BaseContainerMachine(InventoryPlayer playerInventory, IInventory tile) {
        this.tile = tile;
        addMachineSlots(tile);
        bindPlayerInventory(playerInventory);
    }

    protected abstract void addMachineSlots(IInventory tile);
    protected abstract int getMachineSlotCount();

    protected void bindPlayerInventory(InventoryPlayer inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack moved = null;
        Slot slot = (Slot) inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            moved = stack.copy();
            int machineSlots = getMachineSlotCount();
            int invStart = machineSlots;
            int invEnd = inventorySlots.size();

            if (index < machineSlots) {
                if (!mergeItemStack(stack, invStart, invEnd, true)) {
                    return null;
                }
            } else {
                if (!mergeItemStack(stack, 0, machineSlots, false)) {
                    return null;
                }
            }

            if (stack.stackSize == 0) slot.putStack((ItemStack) null);
            else slot.onSlotChanged();
        }
        return moved;
    }
}
