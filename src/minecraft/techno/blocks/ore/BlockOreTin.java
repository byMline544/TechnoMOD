package techno.blocks.ore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon("technomod:oreTin");
    }
}
