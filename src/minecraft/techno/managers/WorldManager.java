package techno.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import techno.storage.world.OreSpawnWorld;

/**
 * Регистрация генерации мира.
 */
public final class WorldManager {
    private WorldManager() {}

    public static void init() {
        GameRegistry.registerWorldGenerator(new OreSpawnWorld());
    }
}
