package domain;

import javax.persistence.*;
import java.util.List;

/**
 * 도메인 모델 엔티티
 */

// 구현의 편리함을 위해 인프라스트럭처에 대한 의존을 일부 도메인에 넣은 코드
// JPA의 @Table 애노테이션을 이용해서 엔티티를 저장할 테이블 이름 지정
@Entity
@Table(name = "purchase_order")
@Access(AccessType.FIELD)
public class Order {

	// 필수값
	public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo,
			OrderState state) {
		setOrderer(orderer);
		setOrderLines(orderLines);
		setShippingInfo(shippingInfo);
		this.state = state;
	}

	private void setOrderer(Orderer orderer) {
		if (orderer == null) {
			throw new IllegalArgumentException("no orderer");
		}
		this.orderer = orderer;
	}

	private void setOrderLines(List<OrderLine> orderLines) {
		verifyAtLeastOneOrMoreOrderLines(orderLines);
		this.orderLines = orderLines;
		calculateTotalAmounts();
	}

	private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
		if (orderLines == null || orderLines.isEmpty()) {
			throw new IllegalArgumentException("no OrderLine");
		}
	}

	private void calculateTotalAmounts() {
		this.totalAmounts = new Money(orderLines.stream()
				.mapToInt(x -> x.getAmounts().getValue()).sum());
	}

	// 엔티티 (pk)
	@EmbeddedId
	private OrderNo orderNumber;

	@Embedded
	private Orderer orderer;

	// OrderState
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private OrderState state;

	public void changeShipped() {
		this.state = OrderState.SHIPPED;
	}

	public void cancel() {
		verifyNotYetShipped();
		this.state = OrderState.CANCELED;
	}

	public void completePayment() {
		this.state = OrderState.DELIVERY_COMPLETED;
	}

	// ShippingInfo
	@Embedded
	private ShippingInfo shippingInfo;

	private void setShippingInfo(ShippingInfo shippingInfo) {
		// 배송지 정보 필수
		if (shippingInfo == null) {
			throw new IllegalArgumentException("no ShippingInfo");
		}
		// ShippingInfo가 불변이므로 새로운 밸류 객체를 전달해서 값을 변경하는 방법밖에 없다.
		this.shippingInfo = shippingInfo;
	}

	// 도메인 모델 엔티티는 도메인 기능도 함께 제공
	public void changeShippingInfo(ShippingInfo newShippingInfo) {
		verifyNotYetShipped();
		setShippingInfo(newShippingInfo);
	}

	private void verifyNotYetShipped() {
		if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING) {
			throw new IllegalArgumentException("already shipped");
		}
	}

	// OrderLine

	public void shipTo(ShippingInfo newShippingInfo, boolean useNewShippingAddrAsMemberAddr) {
		verifyNotYetShipped();
		setShippingInfo(newShippingInfo);
		if (useNewShippingAddrAsMemberAddr) {
			// 하나의 트랜잭션 안에서 다른 애그리거트의 상태를 변경하면 안 됨!
			orderer.getCustomer().changeAddress(newShippingInfo.getAddress());
		}
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
	@OrderColumn(name = "line_idx")
	private List<OrderLine> orderLines;

	@Column(name = "total_amounts")
	private Money totalAmounts; // MoneyConverter를 적용

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj.getClass() != Order.class) {
			return false;
		}

		Order other = (Order) obj;
		if (this.orderNumber == null) {
			return false;
		}
		return this.orderNumber.equals(other.orderNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	public Orderer getOrderer() {
		return new Orderer();
	}

	public Orderer getOrdererId() {
		return new Orderer();
	}
}
