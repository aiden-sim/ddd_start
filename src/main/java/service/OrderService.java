package service;

import dao.OrderRepository;
import domain.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * 애그리거트 객체에 도메인 서비스를 전달하는 것은 응용 서비스 책임이다.
 */
public class OrderService {
	private DiscountCalculationService discountCalculationService;
	private OrderRepository orderRepository;

	@Transactional
	public OrderNo placeOrder(OrderRequest orderRequest) {
		OrderNo orderNo = orderRepository.nextId();
		Order order = createOrder(orderNo, orderRequest);
		orderRepository.save(order);
		return orderNo;

	}

	private Order createOrder(OrderNo orderNo, OrderRequest orderRequest) {
		Member member = findMember(orderRequest.getOrderId());
		Order order = new Order(orderRequest.getOrderer(), orderRequest.getOrderLines(), orderRequest.getShippingInfo(), orderRequest.getOrderState());
		order.calculateAmounts(this.discountCalculationService, member.getGrade());
		return order;
	}

	private Object createOrderer(Member member) {
		return null;
	}

	private Member findMember(Object orderId) {
		return null;
	}
}
