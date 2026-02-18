package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import techno.storage.crafting.electricfurnace.ElectricFurnaceRecipes;

/**
 * Регистрация рецептов.
 */
public final class RecipeManager {
    private RecipeManager() {}

    public static void init() {
        ElectricFurnaceRecipes.init();
        GameRegistry.addSmelting(BlockManager.oreTin.blockID, new ItemStack(ItemManager.ingotTin), 0.7F);
        GameRegistry.addSmelting(BlockManager.oreBronze.blockID, new ItemStack(ItemManager.ingotBronze), 0.7F);
    }
}
