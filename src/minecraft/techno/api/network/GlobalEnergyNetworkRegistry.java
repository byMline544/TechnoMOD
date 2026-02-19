package techno.api.network;

import java.util.HashMap;
import java.util.Map;
import techno.api.energy.EnergyNetwork;

/**
 * Глобальный реестр энергосетей по dimensionId.
 */
public final class GlobalEnergyNetworkRegistry {
    private static final Map<Integer, EnergyNetwork> NETWORKS = new HashMap<Integer, EnergyNetwork>();

    private GlobalEnergyNetworkRegistry() {}

    public static EnergyNetwork get(int dimensionId) {
        EnergyNetwork net = NETWORKS.get(dimensionId);
        if (net == null) {
            net = new EnergyNetwork();
            NETWORKS.put(dimensionId, net);
        }
        return net;
    }
}
