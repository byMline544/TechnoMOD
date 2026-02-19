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
 * - переносит энергию без потери в «никуда».
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

        transferEnergy(sourceList, sinkList);
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

    private void transferEnergy(List<IEnergySource> sourceList, List<IEnergySink> sinkList) {
        int sourceIndex = 0;
        int sourceAvailable = 0;

        for (IEnergySink sink : sinkList) {
            if (!sink.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;

            int need = Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, Integer.MAX_VALUE / 4, true));
            if (need <= 0) continue;

            while (need > 0) {
                if (sourceAvailable <= 0) {
                    sourceAvailable = pullFromNextSource(sourceList, sourceIndex, need);
                    if (sourceAvailable <= 0) {
                        sourceIndex++;
                        while (sourceIndex < sourceList.size()) {
                            sourceAvailable = pullFromNextSource(sourceList, sourceIndex, need);
                            if (sourceAvailable > 0) break;
                            sourceIndex++;
                        }
                        if (sourceAvailable <= 0) return;
                    }
                }

                int offer = Math.min(sourceAvailable, need);
                int accepted = Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, offer, false));
                sourceAvailable -= accepted;
                need -= accepted;
                transferredThisTick += accepted;

                if (accepted <= 0) break;
            }
        }
    }

    private int pullFromNextSource(List<IEnergySource> sourceList, int sourceIndex, int limit) {
        if (sourceIndex < 0 || sourceIndex >= sourceList.size()) return 0;
        IEnergySource source = sourceList.get(sourceIndex);
        if (!source.canConnectEnergy(ForgeDirection.UNKNOWN)) return 0;
        return Math.max(0, source.extractEnergy(ForgeDirection.UNKNOWN, limit, false));
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
