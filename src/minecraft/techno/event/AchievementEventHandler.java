package techno.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import techno.managers.BlockManager;
import techno.managers.ItemManager;
import techno.storage.achievements.TechnoAchievements;

/**
 * Обработчик достижений при получении предметов.
 */
public class AchievementEventHandler {
    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack stack = event.item.getEntityItem();
        if (stack == null) return;

        if (stack.itemID == BlockManager.oreCopper.blockID) {
            player.addStat(TechnoAchievements.achieveOreCopper, 1);
        }
        if (stack.itemID == ItemManager.cableItem.itemID) {
            player.addStat(TechnoAchievements.achieveCable, 1);
        }
        if (stack.itemID == BlockManager.generatorCoal.blockID) {
            player.addStat(TechnoAchievements.achieveGenerator, 1);
        }
        if (stack.itemID == ItemManager.jetpack.itemID) {
            player.addStat(TechnoAchievements.achieveJetpack, 1);
        }
    }
}
