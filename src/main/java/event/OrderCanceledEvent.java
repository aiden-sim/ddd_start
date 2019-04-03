package event;

/**
 * 이벤트
 */
public class OrderCanceledEvent extends Event {
    // 이벤트는 핸들러에서 이벤트를 처리하는 데 필요한 데이터를 포함한다.
    private String orderNumber;

    public OrderCanceledEvent(String number) {
        super();
        this.orderNumber = number;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
