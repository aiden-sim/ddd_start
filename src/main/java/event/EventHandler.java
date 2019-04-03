package event;

import exception.PayloadConvertException;
import typetools.TypeResolver;

public interface EventHandler<T> {
    void handle(T event) throws PayloadConvertException;

    default boolean canHandle(Object event) {
        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(
                EventHandler.class, this.getClass());
        return typeArgs[0].isAssignableFrom(event.getClass());
    }
}
