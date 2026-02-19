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
                if (!mergeItemStack(stack, invStart, invEnd, true)) return null;
            } else {
                if (!mergeToMachineSlots(stack, machineSlots)) return null;
            }

            if (stack.stackSize <= 0) slot.putStack((ItemStack) null);
            else slot.onSlotChanged();
        }

        return moved;
    }

    /**
     * Перенос из инвентаря игрока в слоты машины с проверкой Slot.isItemValid.
     */
    private boolean mergeToMachineSlots(ItemStack stack, int machineSlots) {
        boolean changed = false;

        // Сначала пытаемся сложить в уже занятые подходящие слоты.
        for (int i = 0; i < machineSlots && stack.stackSize > 0; i++) {
            Slot slot = (Slot) inventorySlots.get(i);
            if (!slot.isItemValid(stack)) continue;
            ItemStack slotStack = slot.getStack();
            if (slotStack == null) continue;
            if (!slotStack.isItemEqual(stack) || !ItemStack.areItemStackTagsEqual(slotStack, stack)) continue;

            int max = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
            int canMove = max - slotStack.stackSize;
            if (canMove > 0) {
                int move = Math.min(canMove, stack.stackSize);
                slotStack.stackSize += move;
                stack.stackSize -= move;
                slot.onSlotChanged();
                changed = true;
            }
        }

        // Затем в пустые подходящие слоты.
        for (int i = 0; i < machineSlots && stack.stackSize > 0; i++) {
            Slot slot = (Slot) inventorySlots.get(i);
            if (!slot.isItemValid(stack) || slot.getHasStack()) continue;
            int move = Math.min(stack.stackSize, slot.getSlotStackLimit());
            ItemStack copy = stack.copy();
            copy.stackSize = move;
            slot.putStack(copy);
            slot.onSlotChanged();
            stack.stackSize -= move;
            changed = true;
        }

        return changed;
    }
}
