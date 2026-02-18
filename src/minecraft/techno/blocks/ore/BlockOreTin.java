package techno.blocks.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techno.TechnoMod;

/**
 * Оловянная руда.
 */
public class BlockOreTin extends Block {
    public BlockOreTin(int id) {
        super(id, Material.rock);
        setUnlocalizedName("oreTin");
        setHardness(3.0F);
        setResistance(5.0F);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
        setTextureName("technomod:blocks/oreTin");
    }
}
