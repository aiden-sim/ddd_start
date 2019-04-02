package service;

import dao.OrderRepository;
import domain.Order;
import domain.OrderNo;
import org.springframework.transaction.annotation.Transactional;

public class ChangeShippingService {
	private OrderRepository orderRepository;

	@Transactional
	public void changeShipping(ChangeShippingRequest changeReq) {
		Order order = orderRepository.findById(new OrderNo(changeReq.getNumber()));
		order.changeShippingInfo(changeReq.getShippingInfo());
	}
}
