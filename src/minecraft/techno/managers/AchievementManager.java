package techno.managers;

import cpw.mods.fml.common.registry.LanguageRegistry;
import techno.storage.achievements.TechnoAchievements;

/**
 * Регистрация достижений.
 */
public final class AchievementManager {
    private AchievementManager() {}

    public static void init() {
        TechnoAchievements.init();
        LanguageRegistry.instance().addStringLocalization("achievement.tm_ore_copper", "Найти медную руду");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_ore_copper.desc", "Начало индустриальной эпохи");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_cable", "Сделать кабель");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_cable.desc", "Первый шаг в энергосеть");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_generator", "Собрать генератор");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_generator.desc", "Получить стабильную энергию");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_jetpack", "Собрать джетпак");
        LanguageRegistry.instance().addStringLocalization("achievement.tm_jetpack.desc", "Полетели!");
    }
}
