package techno;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import techno.managers.BlockManager;
import techno.managers.ItemManager;

/**
 * Две креатив-вкладки мода:
 * одна для блоков, вторая для предметов.
 */
public class CreativeTechnoTab extends CreativeTabs {
    private final boolean blocks;

    public CreativeTechnoTab(String label, boolean blocks) {
        super(label);
        this.blocks = blocks;
    }

    @Override
    public Item getTabIconItem() {
        return blocks ? Item.getItemFromBlock(BlockManager.generatorCoal) : ItemManager.ingotBronze;
    }
}
