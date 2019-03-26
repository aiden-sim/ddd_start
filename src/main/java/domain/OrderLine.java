package domain;

/**
 * 최소 한 종류 이상의 상품을 주문해야 한다.
 * 총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.
 */
public class OrderLine {
    private Product product;
    private Money price;
    private int quantity;
    private Money amounts;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

    public Money getAmounts() {
        return amounts;
    }
}
