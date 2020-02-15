package book.chapter3;

import book.chapter1.Product;

public class OrderLine {
    private Product product;
    private int price;
    private int quantity;
    // 한 상품을 한 개 이상 주문할 수 있다.
    private int amounts;

    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    // 각 상품의 구매 가격 합은 상품 가격에 구매 개수를 곱한 값이다.
    private int calculateAmounts() {
        return price * quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmounts() {
        return 0;
    }
}
