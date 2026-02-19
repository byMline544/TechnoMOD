package techno.api.energy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * Энергосеть:
 * - защищена от повторного тика в тот же world tick,
 * - чистит невалидные тайлы,
 * - учитывает приоритеты узлов,
 * - переносит энергию только в пределах реально извлеченного объёма.
 */
public class EnergyNetwork {
    private final Set<IEnergySource> sources = new HashSet<IEnergySource>();
    private final Set<IEnergySink> sinks = new HashSet<IEnergySink>();
    private long transferredThisTick;
    private long lastWorldTick = Long.MIN_VALUE;

    private static final Comparator<IEnergyNode> NODE_PRIORITY_COMPARATOR = new Comparator<IEnergyNode>() {
        @Override
        public int compare(IEnergyNode a, IEnergyNode b) {
            return getPriority(b) - getPriority(a);
        }
    };

    public void addSource(IEnergySource source) { if (source != null) sources.add(source); }
    public void addSink(IEnergySink sink) { if (sink != null) sinks.add(sink); }
    public void removeSource(IEnergySource source) { sources.remove(source); }
    public void removeSink(IEnergySink sink) { sinks.remove(sink); }

    public void tick(long worldTick) {
        if (lastWorldTick == worldTick) return;
        lastWorldTick = worldTick;
        transferredThisTick = 0L;

        cleanupInvalidNodes();
        if (sources.isEmpty() || sinks.isEmpty()) return;

        List<IEnergySource> sourceList = new ArrayList<IEnergySource>(sources);
        List<IEnergySink> sinkList = new ArrayList<IEnergySink>(sinks);
        Collections.sort(sourceList, castComparator());
        Collections.sort(sinkList, castComparator());

        int totalDemand = calculateDemand(sinkList);
        if (totalDemand <= 0) return;

        int energyPool = extractFromSources(sourceList, totalDemand);
        if (energyPool <= 0) return;

        transferredThisTick = distributeToSinks(sinkList, energyPool);
    }

    public long getTransferredThisTick() { return transferredThisTick; }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setLong("TransferredTick", transferredThisTick);
        tag.setInteger("SourcesCount", sources.size());
        tag.setInteger("SinksCount", sinks.size());
        tag.setLong("LastWorldTick", lastWorldTick);
    }

    public void readFromNBT(NBTTagCompound tag) {
        transferredThisTick = tag.getLong("TransferredTick");
        lastWorldTick = tag.getLong("LastWorldTick");
    }

    private int calculateDemand(List<IEnergySink> sinkList) {
        int demanded = 0;
        for (IEnergySink sink : sinkList) {
            if (!sink.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;
            demanded += Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, Integer.MAX_VALUE / 4, true));
            if (demanded < 0) return Integer.MAX_VALUE / 2;
        }
        return demanded;
    }

    private int extractFromSources(List<IEnergySource> sourceList, int demandLimit) {
        int extractedTotal = 0;
        int remainingNeed = demandLimit;

        for (IEnergySource source : sourceList) {
            if (remainingNeed <= 0) break;
            if (!source.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;
            int extracted = Math.max(0, source.extractEnergy(ForgeDirection.UNKNOWN, remainingNeed, false));
            extractedTotal += extracted;
            remainingNeed -= extracted;
        }

        return extractedTotal;
    }

    private int distributeToSinks(List<IEnergySink> sinkList, int available) {
        int transferred = 0;
        int remaining = available;

        for (IEnergySink sink : sinkList) {
            if (remaining <= 0) break;
            if (!sink.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;
            int accepted = Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, remaining, false));
            remaining -= accepted;
            transferred += accepted;
        }

        return transferred;
    }

    private void cleanupInvalidNodes() {
        cleanupInvalidSources();
        cleanupInvalidSinks();
    }

    private void cleanupInvalidSources() {
        Iterator<IEnergySource> it = sources.iterator();
        while (it.hasNext()) {
            IEnergySource source = it.next();
            if (isInvalidTile(source)) it.remove();
        }
    }

    private void cleanupInvalidSinks() {
        Iterator<IEnergySink> it = sinks.iterator();
        while (it.hasNext()) {
            IEnergySink sink = it.next();
            if (isInvalidTile(sink)) it.remove();
        }
    }

    private boolean isInvalidTile(Object node) {
        return node == null || (node instanceof TileEntity && ((TileEntity) node).isInvalid());
    }

    @SuppressWarnings("unchecked")
    private static <T extends IEnergyNode> Comparator<T> castComparator() {
        return (Comparator<T>) NODE_PRIORITY_COMPARATOR;
    }

    private static int getPriority(IEnergyNode node) {
        return node instanceof IEnergyPriority ? ((IEnergyPriority) node).getEnergyPriority() : 0;
    }
}
