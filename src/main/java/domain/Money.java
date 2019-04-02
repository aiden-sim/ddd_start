package domain;

/**
 * 의미를 명확하게 하기위한 밸류 타입
 * 데이터가 하나만 있어도 된다.
 */
public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public int getValue() {
        return value;
    }

    public Money minus(Money discount) {
        return null;
    }
}
