package techno.blocks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * Базовый GUI машины.
 */
public abstract class BaseGuiMachine extends GuiContainer {
    protected BaseGuiMachine(Container container) {
        super(container);
    }
}
