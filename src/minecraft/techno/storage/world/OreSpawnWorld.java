package techno.storage.world;

import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import techno.managers.BlockManager;

/**
 * Генератор руд мира (бронза, олово).
 */
public class OreSpawnWorld implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId != 0) return;
        generateOre(BlockManager.oreTin.blockID, world, random, chunkX, chunkZ, 8, 8, 12, 64);
        generateOre(BlockManager.oreBronze.blockID, world, random, chunkX, chunkZ, 6, 6, 16, 48);
    }

    private void generateOre(int blockId, World world, Random random, int chunkX, int chunkZ, int veinsPerChunk, int veinSize, int minY, int maxY) {
        for (int i = 0; i < veinsPerChunk; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minY + random.nextInt(maxY - minY + 1);
            int z = chunkZ * 16 + random.nextInt(16);
            new WorldGenMinable(blockId, veinSize, Block.stone.blockID).generate(world, random, x, y, z);
        }
    }
}
