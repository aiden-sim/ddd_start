package book.chapter3;

import java.util.List;

public class Order {
    private List<OrderLine> orderLines;
    private Money totalAmounts;

    // 주문할 때 배송지 정보를 반드시 지정해야 한다.
    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }

        this.shippingInfo = shippingInfo;
    }

    public ShippingInfo getShippingInfo() {
        return this.shippingInfo;
    }

    // 최소 한 종류 이상의 상품을 주문해야 한다.
    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    // 총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.
    private void calculateTotalAmounts() {
        int sum = orderLines.stream()
                .mapToInt(ol -> ol.getPrice() * ol.getQuantity())
                .sum();
        this.totalAmounts = new Money(sum);
    }

    private OrderState state;
    private ShippingInfo shippingInfo;

    // 1) 출고 상태로 변경하기
    public void changeShipped() {
        this.state = OrderState.SHIPPED;
    }

    // 2) 배송지 정보 변경하기
    // 출고를 하면 배송지 정보를 변경할 수 없다.
    public void changeShippingInfo(ShippingInfo newShiipingInfo) {
        verifyNotYetShipped();
        this.shippingInfo = newShiipingInfo;
    }

/*    private boolean isShippingChangeable() {
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }*/

    private void verifyNotYetShipped() {
        if (state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING) {
            throw new IllegalArgumentException("can't change shipping in" + state);
        }
    }

    // 3) 주문 취소하기
    // 출고 전에 주문을 취소할 수 있다.
    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    // 4) 결제 완료로 변경하기
    public void completePayment() {

    }

}
