package service;

import dao.OrderRepository;
import domain.Order;
import domain.OrderNo;
import domain.StartShippingRequest;
import exception.VersionConflictException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public class StartShippingService {
	private OrderRepository orderRepository;

	/**
	 * 비선점 잠금
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public void startShipping(StartShippingRequest req) throws VersionConflictException {
		Order order = orderRepository.findById(new OrderNo(req.getOrderNumber()));
		if (!order.matchVersion(req.getVersion())) {
			throw new VersionConflictException();
		}
		order.startShipping();
	}

}
