package techno.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import techno.TechnoMod;

/**
 * Базовый предмет мода.
 */
public class BaseItem extends Item {
    private final String textureName;

    public BaseItem(int id, String name) {
        super(id);
        setUnlocalizedName(name);
        setCreativeTab(TechnoMod.TAB_ITEMS);
        this.textureName = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.itemIcon = register.registerIcon("technomod:" + textureName);
    }
}
