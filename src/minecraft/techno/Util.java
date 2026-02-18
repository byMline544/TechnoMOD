package techno;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Общие утилиты TechnoMod.
 */
public final class Util {
    private Util() {}

    /**
     * Безопасное получение/создание корневого NBT-тега игрока.
     */
    public static NBTTagCompound getPlayerData(EntityPlayer player) {
        if (player.getEntityData() == null) {
            player.setEntityData(new NBTTagCompound());
        }
        return player.getEntityData();
    }

    /**
     * Ограничивает значение диапазоном.
     */
    public static int clampInt(int value, int min, int max) {
        return value < min ? min : Math.min(value, max);
    }
}
