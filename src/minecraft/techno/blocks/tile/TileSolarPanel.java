package techno.blocks.tile;

/**
 * Tile солнечной панели.
 */
public class TileSolarPanel extends BaseTileGenerator {
    public TileSolarPanel() { super(16000); }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            boolean canGen = worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);
            if (canGen) {
                setStoredEnergy(getStoredEnergy() + 4);
            }
            setActiveState(canGen);
        }
    }

    @Override public int getEnergyPriority() { return 30; }

    @Override protected String getInventoryName() { return "solar.panel"; }
}
