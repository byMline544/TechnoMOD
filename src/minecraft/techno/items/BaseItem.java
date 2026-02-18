package techno.items;

import net.minecraft.item.Item;
import techno.TechnoMod;

/**
 * Базовый предмет мода.
 */
public class BaseItem extends Item {
    public BaseItem(int id, String name) {
        super(id);
        setUnlocalizedName(name);
        setCreativeTab(TechnoMod.TAB_ITEMS);
        setTextureName("technomod:items/" + name);
    }
}
