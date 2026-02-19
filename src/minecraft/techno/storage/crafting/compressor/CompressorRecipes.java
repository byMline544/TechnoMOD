package techno.storage.crafting.compressor;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;
import techno.managers.ItemManager;

/**
 * Рецепты компрессора.
 */
public final class CompressorRecipes {
    private static final Map<Integer, ItemStack> RECIPES = new HashMap<Integer, ItemStack>();

    private CompressorRecipes() {}

    public static void init() {
        RECIPES.put(ItemManager.dustBronze.itemID, new ItemStack(ItemManager.ingotBronze));
        RECIPES.put(ItemManager.dustTin.itemID, new ItemStack(ItemManager.ingotTin));
    }

    public static ItemStack getResult(ItemStack input) {
        return input == null ? null : RECIPES.get(input.itemID);
    }
}
