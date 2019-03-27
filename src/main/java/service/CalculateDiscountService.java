package service;

import dao.CustomerRepository;
import dao.OrderRepository;
import domain.*;
import exception.NoCustomerException;
import exception.NoOrderException;

import java.util.List;

public class CalculateDiscountService {
	private OrderRepository orderRepository;

	private CustomerRepository customerRepository;
	private RuleDiscounter ruleDiscounter;

	public CalculateDiscountService(CustomerRepository customerRepository, RuleDiscounter ruleDiscounter, OrderRepository orderRepository) {
		this.customerRepository = customerRepository;
		this.ruleDiscounter = ruleDiscounter;
		this.orderRepository = orderRepository;
	}

	public Money calculateDiscount(List<OrderLine> orderLines, String customerId) {
		Customer customer = findCustomer(customerId);
		return ruleDiscounter.calculateDiscount(customer, orderLines);
	}

	private Customer findCustomer(String customerId) {
		Customer customer = customerRepository.findById(customerId);
		if (customer == null) {
			try {
				throw new NoCustomerException();
			} catch (NoCustomerException e) {
				e.printStackTrace();
			}
		}
		return new Customer();
	}

	public void cancel(OrderNumber number) throws NoOrderException {
		Order order = orderRepository.findByNumber(number);
		if (order == null) {
			throw new NoOrderException(number);
		}
		order.cancel();
	}
}
