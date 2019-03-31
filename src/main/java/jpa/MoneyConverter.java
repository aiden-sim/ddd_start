package jpa;

import domain.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// 모든 Money 타입의 프로퍼티에 대해 MoneyConverter를 자동으로 적용 한다.
@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        if (money == null) {
            return null;
        } else {
            return money.getValue();
        }
    }

    @Override
    public Money convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        } else {
            return new Money(value);
        }
    }
}
