package exception;

import domain.OrderNumber;

public class NoOrderException extends Throwable {
	public NoOrderException(OrderNumber number) {
	}
}
