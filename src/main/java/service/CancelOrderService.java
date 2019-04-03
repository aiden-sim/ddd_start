package service;

import domain.Order;
import domain.OrderNo;
import event.Events;
import event.OrderCanceledEvent;
import org.springframework.transaction.annotation.Transactional;

public class CancelOrderService {
    private RefundService refundService;

    @Transactional
    public void cancel(OrderNo orderNo) {
        // 이벤트 핸들러 등록
        Events.handle(
                (OrderCanceledEvent evt) -> refundService.refund(evt.getOrderNumber())
        );

        Order order = findOrder(orderNo);
        order.cancel();

        // ThreadLocal 변수를 초기화해서 OOME가 발생하지 않도록 함
        Events.reset();

        /*
        order.refundStarted();
        try {
            // 환불이 처리가 길어지면 성능에 영향을 받는다.
            refundService.refund(order.getPaymentId());
            order.refundCompleted();
        } catch (Exception e) {

        }*/
    }

    private Order findOrder(OrderNo orderNo) {
        return null;
    }
}
