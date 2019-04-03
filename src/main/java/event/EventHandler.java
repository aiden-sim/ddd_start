package event;

public interface EventHandler<T> {
	void handle(T event);
}
