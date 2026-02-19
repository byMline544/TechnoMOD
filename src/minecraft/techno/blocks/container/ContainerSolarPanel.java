package techno.blocks.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import techno.blocks.tile.TileSolarPanel;

/**
 * Контейнер солнечной панели.
 */
public class ContainerSolarPanel extends BaseContainerMachine {
    public ContainerSolarPanel(InventoryPlayer playerInventory, TileSolarPanel tile) {
        super(playerInventory, tile);
    }

    @Override
    protected void addMachineSlots(IInventory tile) {
        addSlotToContainer(new Slot(tile, 0, 80, 35));
    }

    @Override
    protected int getMachineSlotCount() {
        return 1;
    }
}
