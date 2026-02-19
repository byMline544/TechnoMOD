package techno.storage.achievements;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import techno.managers.BlockManager;
import techno.managers.ItemManager;

/**
 * Достижения TechnoMod.
 */
public final class TechnoAchievements {
    public static Achievement achieveOreCopper;
    public static Achievement achieveCable;
    public static Achievement achieveGenerator;
    public static Achievement achieveJetpack;

    private TechnoAchievements() {}

    public static void init() {
        achieveOreCopper = new Achievement(9000, "tm_ore_copper", 0, 0, BlockManager.oreCopper, null).registerAchievement();
        achieveCable = new Achievement(9001, "tm_cable", 2, 0, ItemManager.cableItem, achieveOreCopper).registerAchievement();
        achieveGenerator = new Achievement(9002, "tm_generator", 4, 0, BlockManager.generatorCoal, achieveCable).registerAchievement();
        achieveJetpack = new Achievement(9003, "tm_jetpack", 6, 0, ItemManager.jetpack, achieveGenerator).registerAchievement();

        AchievementPage.registerAchievementPage(new AchievementPage("TechnoMod", new Achievement[] {
            achieveOreCopper, achieveCable, achieveGenerator, achieveJetpack
        }));
    }
}
