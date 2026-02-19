package techno.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import techno.managers.ItemManager;

/**
 * Боевая логика игрока и эффекты брони.
 */
public class PlayerCombatEventHandler {
    @ForgeSubscribe
    public void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.entityLiving;

        int reduction = 0;
        for (int i = 0; i < 4; i++) {
            ItemStack armor = player.inventory.armorInventory[i];
            if (armor == null) continue;
            if (armor.itemID == ItemManager.nanoHelmet.itemID || armor.itemID == ItemManager.nanoChest.itemID ||
                armor.itemID == ItemManager.nanoLegs.itemID || armor.itemID == ItemManager.nanoBoots.itemID) {
                reduction += 10;
            }
            if (armor.itemID == ItemManager.quantumHelmet.itemID || armor.itemID == ItemManager.quantumChest.itemID ||
                armor.itemID == ItemManager.quantumLegs.itemID || armor.itemID == ItemManager.quantumBoots.itemID) {
                reduction += 20;
            }
        }

        float factor = Math.max(0.15F, 1.0F - reduction / 100.0F);
        event.ammount *= factor;
    }
}
