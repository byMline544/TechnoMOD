package techno.blocks.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techno.TechnoMod;

/**
 * Бронзовая руда.
 */
public class BlockOreBronze extends Block {
    public BlockOreBronze(int id) {
        super(id, Material.rock);
        setUnlocalizedName("oreBronze");
        setHardness(3.0F);
        setResistance(5.0F);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        setTextureName("technomod:blocks/oreBronze");
    }
}
