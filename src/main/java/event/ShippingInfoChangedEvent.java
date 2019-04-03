package event;

import domain.OrderNo;
import domain.ShippingInfo;

/**
 * 배송지 변경 이벤트 클래스
 */
public class ShippingInfoChangedEvent {
	private String orderNumber; // 주문번호
	private long timestamp; // 이벤트 발생시간
	private ShippingInfo newShippingInfo; // 신규 배송지 정보

	public ShippingInfoChangedEvent(String orderNo, ShippingInfo newShippingInfo) {

	}

	public OrderNo getOrderNo() {
		return null;
	}
}
