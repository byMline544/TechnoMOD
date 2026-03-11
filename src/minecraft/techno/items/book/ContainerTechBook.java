package techno.items.book;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Контейнер книги без слотов (служебный).
 */
public class ContainerTechBook extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
