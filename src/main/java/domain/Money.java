package domain;

public class Money {
    private int value;

    public Money(int sum) {
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public int getValue() {
        return value;
    }
}
