package techno.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Боевая логика игрока.
 */
public class PlayerCombatEventHandler {
    @ForgeSubscribe
    public void onPlayerHurt(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            // Резерв: модификаторы брони/электроброни будут расширены позже.
        }
    }
}
