package techno.items.book;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import techno.items.BaseItem;

/**
 * Предмет тех-книги.
 */
public class ItemTechBook extends BaseItem {
    public ItemTechBook(int id, String name) {
        super(id, name);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        int page = tag.getInteger("Page");
        if (player.isSneaking()) {
            page = (page + 1) % BookPageRepository.count();
            tag.setInteger("Page", page);
            if (!world.isRemote) player.addChatMessage("[TechBook] Страница: " + (page + 1));
        } else if (world.isRemote) {
            openGui(page);
        }
        return stack;
    }

    @SideOnly(Side.CLIENT)
    private void openGui(int page) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiTechBook(page));
    }
}
