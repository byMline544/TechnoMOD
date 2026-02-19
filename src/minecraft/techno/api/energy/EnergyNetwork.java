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
 * - распределяет энергию по фактическому спросу потребителей.
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

        int offered = 0;
        for (IEnergySource source : sourceList) {
            if (!source.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;
            offered += Math.max(0, source.extractEnergy(ForgeDirection.UNKNOWN, Integer.MAX_VALUE / 4, true));
            if (offered < 0) {
                offered = Integer.MAX_VALUE / 2;
                break;
            }
        }
        if (offered <= 0) return;

        int demanded = 0;
        for (IEnergySink sink : sinkList) {
            if (!sink.canConnectEnergy(ForgeDirection.UNKNOWN)) continue;
            demanded += Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, Integer.MAX_VALUE / 4, true));
            if (demanded < 0) {
                demanded = Integer.MAX_VALUE / 2;
                break;
            }
        }

        int transferable = Math.min(offered, demanded <= 0 ? offered : demanded);
        if (transferable <= 0) return;

        int acceptedTotal = 0;
        for (IEnergySink sink : sinkList) {
            if (transferable <= 0 || !sink.canConnectEnergy(ForgeDirection.UNKNOWN)) break;
            int accepted = Math.max(0, sink.receiveEnergy(ForgeDirection.UNKNOWN, transferable, false));
            transferable -= accepted;
            acceptedTotal += accepted;
        }

        int toExtract = acceptedTotal;
        for (IEnergySource source : sourceList) {
            if (toExtract <= 0 || !source.canConnectEnergy(ForgeDirection.UNKNOWN)) break;
            int took = Math.max(0, source.extractEnergy(ForgeDirection.UNKNOWN, toExtract, false));
            toExtract -= took;
        }

        transferredThisTick = acceptedTotal - toExtract;
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
