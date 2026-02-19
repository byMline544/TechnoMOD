package techno.blocks.ore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import techno.TechnoMod;

/**
 * Урановая руда.
 */
public class BlockOreUranium extends Block {
    public BlockOreUranium(int id) {
        super(id, Material.rock);
        setUnlocalizedName("oreUranium");
        setHardness(4.0F);
        setResistance(8.0F);
        setCreativeTab(TechnoMod.TAB_BLOCKS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon("technomod:oreUranium");
    }
}
