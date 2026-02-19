package techno;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Общие утилиты TechnoMod.
 */
public final class Util {
    private Util() {}

    /**
     * Безопасное получение корневого NBT-тега игрока.
     * В 1.5.2 getEntityData() уже возвращает рабочий объект, отдельного setEntityData нет.
     */
    public static NBTTagCompound getPlayerData(EntityPlayer player) {
        return player.getEntityData();
    }

    /**
     * Ограничивает значение диапазоном.
     */
    public static int clampInt(int value, int min, int max) {
        return value < min ? min : Math.min(value, max);
    }
}
