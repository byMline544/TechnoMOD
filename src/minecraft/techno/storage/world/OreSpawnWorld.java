package techno.storage.world;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Генератор руд мира (бронза, олово).
 */
public class OreSpawnWorld implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        // Заглушка генерации: логика жил будет добавлена следующим этапом.
    }
}
