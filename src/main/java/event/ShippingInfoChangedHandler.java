package event;

import dao.OrderRepository;
import domain.Order;

public class ShippingInfoChangedHandler implements EventHandler<ShippingInfoChangedEvent> {
	private OrderRepository orderRepository;

	@Override public void handle(ShippingInfoChangedEvent event) {
		// 이벤트가 필요한 데이터를 담고 있지 않으면,
		// 이벤트 핸들러는 리포지터리, 조회 API, 직접 DB 접근 등의
		// 방식을 통해 필요한 데이터를 조회해야 한다.
		Order order = orderRepository.findById(event.getOrderNo());
		// sync 처리
		// ...
	}
}
