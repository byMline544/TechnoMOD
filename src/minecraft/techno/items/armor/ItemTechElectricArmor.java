package techno.items.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Электроброня с внутренним зарядом.
 */
public class ItemTechElectricArmor extends ItemTechArmor {
    private final int maxCharge;

    public ItemTechElectricArmor(int id, EnumArmorMaterial material, int renderIndex, int armorType, String texture, int maxCharge) {
        super(id, material, renderIndex, armorType, texture);
        this.maxCharge = maxCharge;
        setMaxDamage(1);
    }

    private NBTTagCompound tag(ItemStack stack) {
        if (stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
        return stack.getTagCompound();
    }

    public int getCharge(ItemStack stack) { return tag(stack).getInteger("Charge"); }
    public void setCharge(ItemStack stack, int charge) { tag(stack).setInteger("Charge", Math.max(0, Math.min(maxCharge, charge))); }

    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack) {
        if (world.isRemote) return;
        int charge = getCharge(stack);
        if (charge <= 0) return;

        // Джетпак/электрозащита: мягкое снижение скорости падения.
        if (armorType == 1 && !player.onGround && player.motionY < -0.2D) {
            player.motionY = -0.2D;
            setCharge(stack, charge - 1);
        }

        // Нано/квант штаны: небольшое ускорение бега.
        if (armorType == 2 && player.isSprinting()) {
            setCharge(stack, charge - 1);
        }
    }
}
