package techno.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import techno.TechnoMod;

/**
 * Базовая технологическая броня.
 */
public class ItemTechArmor extends ItemArmor {
    private final String texture;

    public ItemTechArmor(int id, EnumArmorMaterial material, int renderIndex, int armorType, String texture) {
        super(id, material, renderIndex, armorType);
        this.texture = texture;
        setCreativeTab(TechnoMod.TAB_ITEMS);
        setUnlocalizedName(texture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.itemIcon = register.registerIcon("technomod:" + texture);
    }
}
