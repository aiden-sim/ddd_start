package service;

import domain.Customer;
import domain.Money;
import domain.OrderLine;

import java.util.List;

/**
 * 고수준
 * DIP(Dependency inversion principle) 를 적용하면 저수준 모듈이 고수준 모듈에 의존하게 된다.
 * 덕분에 저수준 구현체가 없어도 test code에서 mock이나 스텁(개발되지 않은 코드를 임시로 대치) 을 이용해서 테스트할 수 있다.
 */
public interface RuleDiscounter {
	Money calculateDiscount(Customer customer, List<OrderLine> orderLines);
}
