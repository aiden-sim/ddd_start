package book.chapter1;

public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShiipingInfo) {
        if (!isShippingChangeable()) {
            throw new IllegalArgumentException("can't change shipping in" + state);
        }
        this.shippingInfo = newShiipingInfo;
    }

    private boolean isShippingChangeable() {
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public void changeShipped() {
        this.state = OrderState.SHIPPED;
    }
}
