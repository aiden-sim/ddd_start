package event;

import api.EventEntry;
import exception.PayloadConvertException;

import java.util.List;

/**
 * 이벤트는 데이터가 변경되지 않으므로 수정하는 기능을 제공하지 않는다.
 */
public interface EventStore {
    void save(Object event) throws PayloadConvertException;

    List<EventEntry> get(long offset, long limit);
}
