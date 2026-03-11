package techno.blocks.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import techno.blocks.slot.InputSlot;
import techno.blocks.slot.OutputSlot;
import techno.blocks.tile.TileCompressor;

/** Контейнер компрессора. */
public class ContainerCompressor extends BaseContainerMachine {
    public ContainerCompressor(InventoryPlayer playerInventory, TileCompressor tile) { super(playerInventory, tile); }
    @Override protected void addMachineSlots(IInventory tile) {
        addSlotToContainer(new InputSlot(tile, 0, 56, 35));
        addSlotToContainer(new OutputSlot(tile, 1, 116, 35));
    }
    @Override protected int getMachineSlotCount() { return 2; }
}
