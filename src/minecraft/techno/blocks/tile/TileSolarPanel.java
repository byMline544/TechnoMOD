package techno.blocks.tile;

/**
 * Tile солнечной панели.
 */
public class TileSolarPanel extends BaseTileGenerator {
    public TileSolarPanel() { super(16000); }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote && worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
            setStoredEnergy(getStoredEnergy() + 4);
        }
    }

    @Override
    protected String getInventoryName() { return "solar.panel"; }
}
