package techno.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import techno.blocks.tile.BaseTileMachine;

/**
 * Базовый контейнер машины с безопасным shift-click переносом и синхронизацией GUI.
 */
public abstract class BaseContainerMachine extends Container {
    protected final IInventory tile;
    private int lastEnergy = Integer.MIN_VALUE;
    private int lastMaxEnergy = Integer.MIN_VALUE;
    private int lastProgress = Integer.MIN_VALUE;
    private int lastActive = Integer.MIN_VALUE;

    private int clientEnergyLow;
    private int clientEnergyHigh;
    private int clientMaxEnergyLow;
    private int clientMaxEnergyHigh;

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
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        if (tile instanceof BaseTileMachine) {
            BaseTileMachine machine = (BaseTileMachine) tile;
            sendMachineState(crafter, machine);
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (!(tile instanceof BaseTileMachine)) return;

        BaseTileMachine machine = (BaseTileMachine) tile;
        int energy = machine.getStoredEnergy();
        int maxEnergy = machine.getMaxEnergy();
        int progress = machine.getSyncProcessProgress();
        int active = machine.isActive() ? 1 : 0;

        if (energy == lastEnergy && maxEnergy == lastMaxEnergy && progress == lastProgress && active == lastActive) return;

        for (Object obj : crafters) {
            sendMachineState((ICrafting) obj, machine);
        }

        lastEnergy = energy;
        lastMaxEnergy = maxEnergy;
        lastProgress = progress;
        lastActive = active;
    }

    @Override
    public void updateProgressBar(int id, int value) {
        if (!(tile instanceof BaseTileMachine)) return;

        BaseTileMachine machine = (BaseTileMachine) tile;

        if (id == 0) clientEnergyLow = value & 0xFFFF;
        else if (id == 1) clientEnergyHigh = value & 0xFFFF;
        else if (id == 2) clientMaxEnergyLow = value & 0xFFFF;
        else if (id == 3) clientMaxEnergyHigh = value & 0xFFFF;
        else if (id == 4) machine.setSyncProcessProgress(value);
        else if (id == 5) machine.setActiveForSync(value == 1);

        machine.setMaxEnergyForSync((clientMaxEnergyHigh << 16) | clientMaxEnergyLow);
        machine.setStoredEnergy((clientEnergyHigh << 16) | clientEnergyLow);
    }

    private void sendMachineState(ICrafting crafter, BaseTileMachine machine) {
        int energy = machine.getStoredEnergy();
        int maxEnergy = machine.getMaxEnergy();
        crafter.sendProgressBarUpdate(this, 0, energy & 0xFFFF);
        crafter.sendProgressBarUpdate(this, 1, (energy >>> 16) & 0xFFFF);
        crafter.sendProgressBarUpdate(this, 2, maxEnergy & 0xFFFF);
        crafter.sendProgressBarUpdate(this, 3, (maxEnergy >>> 16) & 0xFFFF);
        crafter.sendProgressBarUpdate(this, 4, machine.getSyncProcessProgress());
        crafter.sendProgressBarUpdate(this, 5, machine.isActive() ? 1 : 0);
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

    private boolean mergeToMachineSlots(ItemStack stack, int machineSlots) {
        boolean changed = false;

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
