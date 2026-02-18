package techno.blocks;

/**
 * Базовый класс источников генерации энергии.
 */
public abstract class BaseBlockGenerator extends BaseBlockMachine {
    protected BaseBlockGenerator(int id, String texture) {
        super(id, texture);
    }
}
