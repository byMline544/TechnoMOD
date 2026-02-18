package techno.api.energy;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

/**
 * Простая энергосеть в стиле IC2:
 * - хранит набор источников и потребителей,
 * - равномерно распределяет энергию,
 * - умеет сохранять состояние в NBT.
 */
public class EnergyNetwork {
    private final Set<IEnergySource> sources = new HashSet<IEnergySource>();
    private final Set<IEnergySink> sinks = new HashSet<IEnergySink>();
    private long transferredThisTick;

    public void addSource(IEnergySource source) { sources.add(source); }
    public void addSink(IEnergySink sink) { sinks.add(sink); }
    public void removeSource(IEnergySource source) { sources.remove(source); }
    public void removeSink(IEnergySink sink) { sinks.remove(sink); }

    /**
     * Тиковое обновление сети:
     * собираем энергию со всех источников и распределяем по потребителям.
     */
    public void tick() {
        transferredThisTick = 0L;
        if (sources.isEmpty() || sinks.isEmpty()) {
            return;
        }

        int offered = 0;
        for (IEnergySource source : sources) {
            offered += source.extractEnergy(ForgeDirection.UNKNOWN, Integer.MAX_VALUE / 4, true);
        }

        int remaining = offered;
        for (IEnergySink sink : sinks) {
            if (remaining <= 0) break;
            int accepted = sink.receiveEnergy(ForgeDirection.UNKNOWN, remaining, false);
            remaining -= accepted;
            transferredThisTick += accepted;
        }

        int consumed = offered - remaining;
        for (IEnergySource source : sources) {
            if (consumed <= 0) break;
            int took = source.extractEnergy(ForgeDirection.UNKNOWN, consumed, false);
            consumed -= took;
        }
    }

    public long getTransferredThisTick() { return transferredThisTick; }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setLong("TransferredTick", transferredThisTick);
        tag.setInteger("SourcesCount", sources.size());
        tag.setInteger("SinksCount", sinks.size());
    }

    public void readFromNBT(NBTTagCompound tag) {
        transferredThisTick = tag.getLong("TransferredTick");
    }
}
