package book.chapter2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelOrderService {

    // 주문 취소 기능을 제공하는 응용 서비스 예제
    @Transactional
    public void cancelOrder(String orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        if (order == null)
            throw new OrderNotFoundException(orderId);
        order.cancel();
    }

    private Order findOrderById(String orderId) {
        return new Order(null, null);
    }
}