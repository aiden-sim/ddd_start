package event;

public interface OffsetStore {
    long get();

    void update(long nextOffset);
}
