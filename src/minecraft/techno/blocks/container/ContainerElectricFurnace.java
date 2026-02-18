package techno.blocks.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import techno.blocks.slot.InputSlot;
import techno.blocks.slot.OutputSlot;
import techno.blocks.tile.TileElectricFurnace;

/**
 * Контейнер электропечи.
 */
public class ContainerElectricFurnace extends BaseContainerMachine {
    public ContainerElectricFurnace(InventoryPlayer playerInventory, TileElectricFurnace tile) {
        super(playerInventory, tile);
        addSlotToContainer(new InputSlot(tile, 0, 56, 35));
        addSlotToContainer(new OutputSlot(tile, 1, 116, 35));
    }
}
