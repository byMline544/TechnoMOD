package techno.items;

/**
 * Базовый электрический предмет.
 */
public class BaseElectricItem extends BaseItem {
    protected int energy;
    protected int maxEnergy;

    public BaseElectricItem(int id, String name, int maxEnergy) {
        super(id, name);
        this.maxEnergy = maxEnergy;
    }
}
