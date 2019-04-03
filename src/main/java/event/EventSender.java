package event;

import api.EventEntry;

public interface EventSender {
    void send(EventEntry entry);
}
