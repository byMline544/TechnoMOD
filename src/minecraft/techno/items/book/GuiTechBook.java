package techno.items.book;

import net.minecraft.client.gui.GuiScreen;

/**
 * GUI тех-книги.
 */
public class GuiTechBook extends GuiScreen {
    private int page;

    public GuiTechBook(int startPage) {
        this.page = startPage;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Техно-книга", width / 2, 16, 0xFFFFFF);
        fontRenderer.drawSplitString(BookPageRepository.getPage(page), width / 2 - 110, 40, 220, 0xE0E0E0);
        drawCenteredString(fontRenderer, "Страница " + (page + 1) + "/" + BookPageRepository.count() + "  (← / →)", width / 2, height - 24, 0xAAAAAA);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        if (keyCode == 205 && page < BookPageRepository.count() - 1) page++;
        if (keyCode == 203 && page > 0) page--;
        if (keyCode == 1 || keyCode == 18) mc.displayGuiScreen(null);
    }
}
