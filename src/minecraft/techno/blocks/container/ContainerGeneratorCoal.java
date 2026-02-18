package techno.blocks.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import techno.blocks.tile.TileGeneratorCoal;

/**
 * Контейнер генератора.
 */
public class ContainerGeneratorCoal extends BaseContainerMachine {
    public ContainerGeneratorCoal(InventoryPlayer playerInventory, TileGeneratorCoal tile) {
        super(playerInventory, tile);
        addSlotToContainer(new Slot(tile, 0, 80, 35));
    }
}
