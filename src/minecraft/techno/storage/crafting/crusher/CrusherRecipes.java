package techno.storage.crafting.crusher;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import techno.managers.ItemManager;

/**
 * Рецепты дробителя.
 */
public final class CrusherRecipes {
    private static final Map<Integer, ItemStack> RECIPES = new HashMap<Integer, ItemStack>();

    private CrusherRecipes() {}

    public static void init() {
        RECIPES.put(Block.oreIron.blockID, new ItemStack(ItemManager.dustTin, 2));
        RECIPES.put(Block.oreGold.blockID, new ItemStack(ItemManager.dustBronze, 2));
    }

    public static ItemStack getResult(ItemStack input) {
        return input == null ? null : RECIPES.get(input.itemID);
    }
}
