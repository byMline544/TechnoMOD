package techno.blocks.tile;

/**
 * Tile солнечной панели.
 */
public class TileSolarPanel extends BaseTileGenerator {
    public TileSolarPanel() { super(16000); }

    @Override
    public void updateEntity() {
        if (worldObj != null && worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
            setStoredEnergy(getStoredEnergy() + 4);
        }
    }
}
