package techno.blocks.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import techno.api.energy.IEnergyNode;
import techno.api.energy.IEnergyPriority;

/**
 * Базовый tile машин с хранением энергии, инвентарем и NBT.
 */
public abstract class BaseTileMachine extends TileEntity implements IEnergyNode, IEnergyPriority, IInventory {
    protected int energy;
    protected int maxEnergy;
    protected final ItemStack[] inventory;
    protected boolean active;

    protected BaseTileMachine(int maxEnergy, int inventorySize) {
        this.maxEnergy = maxEnergy;
        this.inventory = new ItemStack[inventorySize];
    }

    @Override public int getStoredEnergy() { return energy; }
    @Override public int getMaxEnergy() { return maxEnergy; }
    @Override public void setStoredEnergy(int amount) { energy = Math.max(0, Math.min(maxEnergy, amount)); }
    public void setMaxEnergyForSync(int value) { maxEnergy = Math.max(0, value); }
    public void setActiveForSync(boolean value) { active = value; }
    @Override public int getEnergyPriority() { return 0; }

    public boolean isActive() { return active; }

    public int getSyncProcessProgress() { return 0; }
    public void setSyncProcessProgress(int value) {}

    protected void setActiveState(boolean value) {
        if (active != value) {
            active = value;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        energy = nbt.getInteger("Energy");
        maxEnergy = nbt.getInteger("MaxEnergy");
        active = nbt.getBoolean("Active");

        NBTTagList list = nbt.getTagList("Items");
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound itemTag = (NBTTagCompound) list.tagAt(i);
            int slot = itemTag.getByte("Slot") & 255;
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Energy", energy);
        nbt.setInteger("MaxEnergy", maxEnergy);
        nbt.setBoolean("Active", active);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override public int getSizeInventory() { return inventory.length; }
    @Override public ItemStack getStackInSlot(int i) { return inventory[i]; }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        if (inventory[i] == null) return null;
        if (inventory[i].stackSize <= count) {
            ItemStack result = inventory[i];
            inventory[i] = null;
            return result;
        }
        ItemStack result = inventory[i].splitStack(count);
        if (inventory[i].stackSize <= 0) inventory[i] = null;
        return result;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack stack = inventory[i];
        inventory[i] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        inventory[i] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) stack.stackSize = getInventoryStackLimit();
    }

    @Override public int getInventoryStackLimit() { return 64; }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
            player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
    }

    @Override public void openChest() {}
    @Override public void closeChest() {}
    @Override public String getInvName() { return getInventoryName(); }
    @Override public boolean isInvNameLocalized() { return true; }
    @Override public boolean isStackValidForSlot(int i, ItemStack stack) { return true; }

    protected String getInventoryName() { return "techno.machine"; }
}
