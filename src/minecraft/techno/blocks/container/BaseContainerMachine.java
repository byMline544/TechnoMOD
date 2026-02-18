package techno.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Базовый контейнер машины.
 */
public abstract class BaseContainerMachine extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
