package exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PayloadConvertException extends Throwable {
    public PayloadConvertException(JsonProcessingException e) {
    }
}
