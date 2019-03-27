package service;

import dao.CustomerRepository;
import dao.OrderRepository;
import domain.Customer;
import domain.Order;
import domain.OrderId;
import domain.ShippingInfo;
import exception.OrderNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class ChangeOrderService {
	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private boolean useNewshippingAddrAsMemberAddr;

	public ChangeOrderService(OrderRepository orderRepository, CustomerRepository customerRepository, boolean useNewshippingAddrAsMemberAddr) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.useNewshippingAddrAsMemberAddr = useNewshippingAddrAsMemberAddr;
	}

	// 두 개 이상의 애그리거트를 변경해야 하면, 응용 서비스에서 각 애그리거트의 상태를 변경한다.
	/*
	@Transactional
	public void changeShippingInfo(OrderId id, ShippingInfo newShippingInfo) throws OrderNotFoundException {
		Order order = orderRepository.findById(id);
		if (order == null) {
			throw new OrderNotFoundException();
		}

		order.shipTo(newShippingInfo, useNewshippingAddrAsMemberAddr);
		if (useNewshippingAddrAsMemberAddr) {
			order.getOrderer().getCustomer().changeAddress(newShippingInfo.getAddress());
		}
	}*/

	@Transactional
	public void changeShippingInfo(OrderId id, ShippingInfo newShippingInfo) throws OrderNotFoundException {
		Order order = orderRepository.findById(id);
		if (order == null) {
			throw new OrderNotFoundException();
		}
		order.changeShippingInfo(newShippingInfo);
		if (useNewshippingAddrAsMemberAddr) {
			// ID를 이용해서 참조하는 애그리거트를 구한다.
			// 응용 서비스에서 필요한 애그리거트를 로딩하므로 애그리거트 수준에서 지연 로딩을 하는 것과 동일하다.
			Customer customer = customerRepository.findById(
					order.getOrderer().getCustomerId());
			customer.changeAddress(newShippingInfo.getAddress());
		}
	}
}
