package techno.blocks.gui;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import techno.blocks.container.ContainerElectricFurnace;

/**
 * GUI машины.
 */
public class GuiElectricFurnace extends BaseGuiMachine {
    public GuiElectricFurnace() {
        super(new ContainerElectricFurnace());
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTextureByName("/mods/technomod/textures/gui/guiElectricFurnace.png");
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
