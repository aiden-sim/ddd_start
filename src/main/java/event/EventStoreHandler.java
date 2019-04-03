package event;

import exception.PayloadConvertException;
import org.springframework.beans.factory.annotation.Autowired;

public class EventStoreHandler implements EventHandler<Object> {
    private EventStore eventStore;

    @Override
    public void handle(Object event) throws PayloadConvertException {
        eventStore.save(event);
    }

    @Autowired
    public void setEventStore(EventStore eventStore) {
        this.eventStore = eventStore;
    }
}
