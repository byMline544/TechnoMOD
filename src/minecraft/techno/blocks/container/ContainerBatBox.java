package techno.blocks.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import techno.blocks.tile.TileBatBox;

/** Контейнер BatBox. */
public class ContainerBatBox extends BaseContainerMachine {
    public ContainerBatBox(InventoryPlayer playerInventory, TileBatBox tile) {
        super(playerInventory, tile);
    }

    @Override
    protected void addMachineSlots(IInventory tile) {}

    @Override
    protected int getMachineSlotCount() {
        return 0;
    }
}
