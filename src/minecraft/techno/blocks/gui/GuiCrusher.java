package techno.blocks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import techno.blocks.container.ContainerCrusher;
import techno.blocks.tile.TileCrusher;

/** GUI дробителя. */
public class GuiCrusher extends BaseGuiMachine {
    private final TileCrusher tile;
    public GuiCrusher(InventoryPlayer inv, TileCrusher tile) {
        super(new ContainerCrusher(inv, tile));
        this.tile = tile;
        this.xSize = 176; this.ySize = 166;
    }

    @Override protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString("Дробитель", 8, 6, 0x404040);
        fontRenderer.drawString("Энергия: " + tile.getStoredEnergy() + "/" + tile.getMaxEnergy() + " te", 8, 18, 0x00E060);
    }
    @Override protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1F,1F,1F,1F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/technomod/textures/gui/guiCrusher.png");
        int x=(width-xSize)/2; int y=(height-ySize)/2;
        drawTexturedModalRect(x,y,0,0,xSize,ySize);
    }
}
