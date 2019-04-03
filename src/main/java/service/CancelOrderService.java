package service;

import domain.Order;
import domain.OrderNo;
import org.springframework.transaction.annotation.Transactional;

public class CancelOrderService {
	private RefundService refundService;

	@Transactional
	public void cancel(OrderNo orderNo) {
		Order order = findOrder(orderNo);
		order.cancel();

		order.refundStarted();
		try {
			// 환불이 처리가 길어지면 성능에 영향을 받는다.
			refundService.refund(order.getPaymentId());
			order.refundCompleted();
		} catch (Exception e) {

		}
	}

	private Order findOrder(OrderNo orderNo) {
		return null;
	}
}
