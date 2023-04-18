package commandManager.commands;

/**
 * Provides Argument Consumer
 *
 * @param <T> Argument param
 * @author worthant
 * @since 2.0
 */
public interface ArgumentConsumer<T> {
    void setObj(T obj);
}
