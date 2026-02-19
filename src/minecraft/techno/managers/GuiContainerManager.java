package techno.managers;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import techno.TechnoMod;
import techno.blocks.container.ContainerElectricFurnace;
import techno.blocks.container.ContainerGeneratorCoal;
import techno.blocks.container.ContainerSolarPanel;
import techno.blocks.container.ContainerCompressor;
import techno.blocks.container.ContainerCrusher;
import techno.blocks.gui.GuiElectricFurnace;
import techno.blocks.gui.GuiGeneratorCoal;
import techno.blocks.gui.GuiSolarPanel;
import techno.blocks.gui.GuiCrusher;
import techno.blocks.gui.GuiCompressor;
import techno.blocks.tile.TileElectricFurnace;
import techno.blocks.tile.TileGeneratorCoal;
import techno.blocks.tile.TileSolarPanel;
import techno.blocks.tile.TileCrusher;
import techno.blocks.tile.TileCompressor;

/**
 * Регистрация GUI обработчика для открытия контейнеров/GUI машин.
 */
public final class GuiContainerManager {
    private GuiContainerManager() {}

    public static final int GUI_GENERATOR = 1;
    public static final int GUI_SOLAR = 2;
    public static final int GUI_ELECTRIC_FURNACE = 3;
    public static final int GUI_CRUSHER = 4;
    public static final int GUI_COMPRESSOR = 5;

    public static void init() {
        NetworkRegistry.instance().registerGuiHandler(TechnoMod.instance, new TechnoGuiHandler());
    }

    private static class TechnoGuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == GUI_GENERATOR) {
                return new ContainerGeneratorCoal(player.inventory, (TileGeneratorCoal) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_SOLAR) {
                return new ContainerSolarPanel(player.inventory, (TileSolarPanel) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_ELECTRIC_FURNACE) {
                return new ContainerElectricFurnace(player.inventory, (TileElectricFurnace) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_CRUSHER) {
                return new ContainerCrusher(player.inventory, (TileCrusher) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_COMPRESSOR) {
                return new ContainerCompressor(player.inventory, (TileCompressor) world.getBlockTileEntity(x, y, z));
            }

            return null;
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == GUI_GENERATOR) {
                return new GuiGeneratorCoal(player.inventory, (TileGeneratorCoal) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_SOLAR) {
                return new GuiSolarPanel(player.inventory, (TileSolarPanel) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_ELECTRIC_FURNACE) {
                return new GuiElectricFurnace(player.inventory, (TileElectricFurnace) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_CRUSHER) {
                return new GuiCrusher(player.inventory, (TileCrusher) world.getBlockTileEntity(x, y, z));
            }
            if (id == GUI_COMPRESSOR) {
                return new GuiCompressor(player.inventory, (TileCompressor) world.getBlockTileEntity(x, y, z));
            }

            return null;
        }
    }
}
