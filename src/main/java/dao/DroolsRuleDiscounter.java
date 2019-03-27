package dao;

import domain.Customer;
import domain.Money;
import domain.OrderLine;
import service.RuleDiscounter;

import java.util.List;

/**
 * 저수준
 */
public class DroolsRuleDiscounter implements RuleDiscounter {
	@Override public Money calculateDiscount(Customer customer, List<OrderLine> orderLines) {
		return null;
	}
}
