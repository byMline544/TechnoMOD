package techno.storage.crafting.electricfurnace;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Рецепты электропечи.
 *
 * Логика:
 * 1) сначала кастомные рецепты мода,
 * 2) затем общий ванильный реестр плавки (включая модовые addSmelting).
 */
public final class ElectricFurnaceRecipes {
    private static final Map<Integer, ItemStack> CUSTOM_RECIPES = new HashMap<Integer, ItemStack>();

    private ElectricFurnaceRecipes() {}

    public static void init() {
        CUSTOM_RECIPES.clear();
    }

    public static void registerCustom(int inputItemId, ItemStack output) {
        if (output != null) CUSTOM_RECIPES.put(inputItemId, output.copy());
    }

    public static ItemStack getResult(ItemStack input) {
        if (input == null) return null;

        ItemStack custom = CUSTOM_RECIPES.get(input.itemID);
        if (custom != null) return custom;

        ItemStack vanilla = FurnaceRecipes.smelting().getSmeltingResult(input);
        return vanilla == null ? null : vanilla.copy();
    }
}
