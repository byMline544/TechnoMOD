package techno.items.book;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * GUI тех-книги.
 */
public class GuiTechBook extends GuiScreen {
    private int page;
    private GuiButton prevButton;
    private GuiButton nextButton;

    public GuiTechBook(int startPage) {
        this.page = startPage;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        int centerX = width / 2;
        int y = height - 30;
        prevButton = new GuiButton(1, centerX - 90, y, 20, 20, "<");
        nextButton = new GuiButton(2, centerX + 70, y, 20, 20, ">");

        buttonList.add(prevButton);
        buttonList.add(nextButton);
        updateButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Техно-книга", width / 2, 16, 0xFFFFFF);
        fontRenderer.drawSplitString(BookPageRepository.getPage(page), width / 2 - 110, 40, 220, 0xE0E0E0);
        drawCenteredString(fontRenderer, "Страница " + (page + 1) + "/" + BookPageRepository.count(), width / 2, height - 24, 0xAAAAAA);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1 && page > 0) page--;
        if (button.id == 2 && page < BookPageRepository.count() - 1) page++;
        updateButtons();
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        if (keyCode == 205 && page < BookPageRepository.count() - 1) page++;
        if (keyCode == 203 && page > 0) page--;
        updateButtons();
        if (keyCode == 1 || keyCode == 18) mc.displayGuiScreen(null);
    }

    private void updateButtons() {
        if (prevButton != null) prevButton.enabled = page > 0;
        if (nextButton != null) nextButton.enabled = page < BookPageRepository.count() - 1;
    }
}
