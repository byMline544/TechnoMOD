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
        GameRegistry.addSmelting(BlockManager.oreUranium.blockID, new ItemStack(ItemManager.ingotUranium), 1.0F);

        GameRegistry.addRecipe(new ItemStack(ItemManager.plateBronze), new Object[]{"II", "II", 'I', ItemManager.ingotBronze});
        GameRegistry.addRecipe(new ItemStack(ItemManager.machineCasing), new Object[]{"III", "I I", "III", 'I', Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(ItemManager.cableItem, 6), new Object[]{"CCC", 'C', ItemManager.ingotCopper});
        GameRegistry.addRecipe(new ItemStack(ItemManager.battery), new Object[]{" T ", "TRT", " T ", 'T', ItemManager.ingotTin, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(ItemManager.energyCrystal), new Object[]{"RRR", "RBR", "RRR", 'R', Item.redstone, 'B', ItemManager.battery});
        GameRegistry.addRecipe(new ItemStack(ItemManager.lapotronCrystal), new Object[]{"EEE", "EDE", "EEE", 'E', ItemManager.energyCrystal, 'D', Item.diamond});
        GameRegistry.addRecipe(new ItemStack(ItemManager.circuitBasic), new Object[]{"CRC", "RBR", "CRC", 'C', ItemManager.cableItem, 'R', Item.redstone, 'B', ItemManager.battery});
        GameRegistry.addRecipe(new ItemStack(ItemManager.reactorCore), new Object[]{"UUU", "CMC", "UUU", 'U', ItemManager.ingotUranium, 'C', ItemManager.circuitBasic, 'M', ItemManager.machineCasing});

        GameRegistry.addRecipe(new ItemStack(BlockManager.cable, 8), new Object[]{"CCC", 'C', ItemManager.cableItem});
        GameRegistry.addRecipe(new ItemStack(BlockManager.crusher), new Object[]{"IPI", "CMC", "III", 'I', Item.ingotIron, 'P', Block.pistonBase, 'C', ItemManager.circuitBasic, 'M', BlockManager.cable});
        GameRegistry.addRecipe(new ItemStack(BlockManager.compressor), new Object[]{"III", "CMC", "IPI", 'I', Item.ingotIron, 'P', Block.pistonBase, 'C', ItemManager.circuitBasic, 'M', BlockManager.cable});
        GameRegistry.addRecipe(new ItemStack(BlockManager.batBox), new Object[]{"CCC", "BMB", "CCC", 'C', ItemManager.cableItem, 'B', ItemManager.battery, 'M', ItemManager.machineCasing});

        GameRegistry.addRecipe(new ItemStack(ItemManager.jetpack), new Object[]{"CBC", "BPB", " C ", 'C', ItemManager.cableItem, 'B', ItemManager.battery, 'P', ItemManager.plateBronze});

        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoHelmet), new Object[]{"CCC", "CEC", "   ", 'C', ItemManager.circuitBasic, 'E', ItemManager.energyCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoChest), new Object[]{"C C", "CEC", "CCC", 'C', ItemManager.circuitBasic, 'E', ItemManager.energyCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoLegs), new Object[]{"CEC", "C C", "C C", 'C', ItemManager.circuitBasic, 'E', ItemManager.energyCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.nanoBoots), new Object[]{"   ", "C C", "CEC", 'C', ItemManager.circuitBasic, 'E', ItemManager.energyCrystal});

        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumHelmet), new Object[]{"NNN", "NLN", "   ", 'N', ItemManager.nanoHelmet, 'L', ItemManager.lapotronCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumChest), new Object[]{"N N", "NLN", "NNN", 'N', ItemManager.nanoChest, 'L', ItemManager.lapotronCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumLegs), new Object[]{"NLN", "N N", "N N", 'N', ItemManager.nanoLegs, 'L', ItemManager.lapotronCrystal});
        GameRegistry.addRecipe(new ItemStack(ItemManager.quantumBoots), new Object[]{"   ", "N N", "NLN", 'N', ItemManager.nanoBoots, 'L', ItemManager.lapotronCrystal});
    }
}
