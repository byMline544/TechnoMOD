package techno.items.book;

/**
 * Репозиторий страниц тех-книги.
 */
public final class BookPageRepository {
    private static final String[] PAGES = new String[] {
        "TechnoMod\n\nДобро пожаловать в техно-эру!\nИспользуйте руды, кабели и генераторы.",
        "Энергия te\n\nГенератор и солнечная панель вырабатывают te.\nКабели передают с потерями.",
        "Механизмы\n\nЭлектропечь: плавка\nДробитель: измельчение\nКомпрессор: прессование",
        "Броня\n\nДжетпак и нано/квант наборы используют внутренний заряд."
    };

    private BookPageRepository() {}

    public static String getPage(int index) {
        if (index < 0) index = 0;
        if (index >= PAGES.length) index = PAGES.length - 1;
        return PAGES[index];
    }

    public static int count() {
        return PAGES.length;
    }
}
