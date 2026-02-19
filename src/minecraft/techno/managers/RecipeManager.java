package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import techno.storage.crafting.compressor.CompressorRecipes;
import techno.storage.crafting.crusher.CrusherRecipes;
import techno.storage.crafting.electricfurnace.ElectricFurnaceRecipes;

/**
 * Регистрация рецептов.
 */
public final class RecipeManager {
    private RecipeManager() {}

    public static void init() {
        ElectricFurnaceRecipes.init();
        CrusherRecipes.init();
        CompressorRecipes.init();

        GameRegistry.addSmelting(BlockManager.oreTin.blockID, new ItemStack(ItemManager.ingotTin), 0.7F);
        GameRegistry.addSmelting(BlockManager.oreBronze.blockID, new ItemStack(ItemManager.ingotBronze), 0.7F);
        GameRegistry.addSmelting(BlockManager.oreCopper.blockID, new ItemStack(ItemManager.ingotCopper), 0.7F);

        GameRegistry.addRecipe(new ItemStack(ItemManager.plateBronze), new Object[]{"II", "II", 'I', ItemManager.ingotBronze});
        GameRegistry.addRecipe(new ItemStack(ItemManager.cableItem, 6), new Object[]{"CCC", 'C', ItemManager.ingotCopper});
        GameRegistry.addRecipe(new ItemStack(ItemManager.battery), new Object[]{" T ", "TRT", " T ", 'T', ItemManager.ingotTin, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(ItemManager.circuitBasic), new Object[]{"CRC", "RBR", "CRC", 'C', ItemManager.cableItem, 'R', Item.redstone, 'B', ItemManager.battery});

        GameRegistry.addRecipe(new ItemStack(BlockManager.cable, 8), new Object[]{"CCC", 'C', ItemManager.cableItem});
        GameRegistry.addRecipe(new ItemStack(BlockManager.crusher), new Object[]{"IPI", "CMC", "III", 'I', Item.ingotIron, 'P', Block.pistonBase, 'C', ItemManager.circuitBasic, 'M', BlockManager.cable});
        GameRegistry.addRecipe(new ItemStack(BlockManager.compressor), new Object[]{"III", "CMC", "IPI", 'I', Item.ingotIron, 'P', Block.pistonBase, 'C', ItemManager.circuitBasic, 'M', BlockManager.cable});

        GameRegistry.addRecipe(new ItemStack(ItemManager.jetpack), new Object[]{"CBC", "BPB", " C ", 'C', ItemManager.cableItem, 'B', ItemManager.battery, 'P', ItemManager.plateBronze});

        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoHelmet), new Object[]{"CCC", "C C", "   ", 'C', ItemManager.circuitBasic});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoChest), new Object[]{"C C", "CCC", "CCC", 'C', ItemManager.circuitBasic});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoLegs), new Object[]{"CCC", "C C", "C C", 'C', ItemManager.circuitBasic});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoBoots), new Object[]{"   ", "C C", "C C", 'C', ItemManager.circuitBasic});

        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumHelmet), new Object[]{"NNN", "NRN", "   ", 'N', ItemManager.nanoHelmet, 'R', Item.netherStar});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumChest), new Object[]{"N N", "NRN", "NNN", 'N', ItemManager.nanoChest, 'R', Item.netherStar});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumLegs), new Object[]{"NNN", "N N", "N N", 'N', ItemManager.nanoLegs});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumBoots), new Object[]{"   ", "N N", "N N", 'N', ItemManager.nanoBoots});
    }
}
