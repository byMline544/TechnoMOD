package techno.blocks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import techno.blocks.container.ContainerGeneratorCoal;
import techno.blocks.tile.TileGeneratorCoal;

/**
 * GUI угольного генератора.
 */
public class GuiGeneratorCoal extends BaseGuiMachine {
    public GuiGeneratorCoal(InventoryPlayer inv, TileGeneratorCoal tile) {
        super(new ContainerGeneratorCoal(inv, tile));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/technomod/textures/gui/guiGeneratorCoal.png");
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
