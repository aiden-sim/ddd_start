package domain;

public class MutableMoney {
	public MutableMoney(int i) {

	}

	public Money toImmutableMoney() {
		return new Money(1000);
	}
}
