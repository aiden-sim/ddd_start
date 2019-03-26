package domain;

import java.util.List;

public class Order {

    // 필수값

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo,
                 OrderState state) {
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.state = state;
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) {
            throw new IllegalArgumentException("no orderer");
        }
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream()
                .mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    // 엔티티 (pk)
    private String orderNumber;

    private Orderer orderer;

    // OrderState
    private OrderState state;

    public void changeShipped() {
        this.state = OrderState.SHIPPED;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    public void completePayment() {
        this.state = OrderState.DELIVERY_COMPLETED;
    }

    // ShippingInfo
    private ShippingInfo shippingInfo;

    private void setShippingInfo(ShippingInfo shippingInfo) {
        // 배송지 정보 필수
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
    }

    private void verifyNotYetShipped() {
        if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING) {
            throw new IllegalArgumentException("already shipped");
        }
    }

    // OrderLine
    private List<OrderLine> orderLines;
    private Money totalAmounts;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass() != Order.class) {
            return false;
        }

        Order other = (Order) obj;
        if (this.orderNumber == null) {
            return false;
        }
        return this.orderNumber.equals(other.orderNumber);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        return result;
    }
}
