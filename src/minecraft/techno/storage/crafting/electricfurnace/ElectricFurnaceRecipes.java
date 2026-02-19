package techno.storage.crafting.electricfurnace;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;
import techno.managers.ItemManager;

/**
 * Рецепты электропечи.
 */
public final class ElectricFurnaceRecipes {
    private static final Map<Integer, ItemStack> RECIPES = new HashMap<Integer, ItemStack>();

    private ElectricFurnaceRecipes() {}

    public static void init() {
        RECIPES.put(15, new ItemStack(ItemManager.ingotTin));
    }

    public static ItemStack getResult(ItemStack input) {
        return input == null ? null : RECIPES.get(input.itemID);
    }
}
