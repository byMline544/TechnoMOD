package techno.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Расширенные данные игрока (IExtendedEntityProperties).
 */
public class ExtendedPlayer implements IExtendedEntityProperties {
    public static final String KEY = "TechnoPlayer";

    private int personalEnergy;

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound own = new NBTTagCompound();
        own.setInteger("PersonalEnergy", personalEnergy);
        compound.setTag(KEY, own);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if (compound.hasKey(KEY)) {
            NBTTagCompound own = compound.getCompoundTag(KEY);
            personalEnergy = own.getInteger("PersonalEnergy");
        }
    }

    @Override
    public void init(Entity entity, World world) {}

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(KEY, new ExtendedPlayer());
    }
}
