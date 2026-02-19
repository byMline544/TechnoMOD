package techno.api.energy;

/**
 * Необязательный контракт приоритета для узлов энергосети.
 * Чем выше значение — тем раньше узел обслуживается в сети.
 */
public interface IEnergyPriority {
    int getEnergyPriority();
}
