package techno.blocks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import techno.blocks.container.ContainerElectricFurnace;
import techno.blocks.tile.TileElectricFurnace;

/**
 * GUI электропечи.
 */
public class GuiElectricFurnace extends BaseGuiMachine {
    private final TileElectricFurnace tile;

    public GuiElectricFurnace(InventoryPlayer inv, TileElectricFurnace tile) {
        super(new ContainerElectricFurnace(inv, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString("Электропечь", 8, 6, 0x404040);
        fontRenderer.drawString("Энергия: " + tile.getStoredEnergy() + "/" + tile.getMaxEnergy() + " te", 8, 18, 0x00E060);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/technomod/textures/gui/guiElectricFurnace.png");
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
