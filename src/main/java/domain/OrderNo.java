package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * JPA에서 식별자 타입은 Serializable 타입이어야 한다.
 */
@Embeddable
public class OrderNo implements Serializable {
	@Column(name = "order_number")
	private String number;

	public OrderNo(String number) {
		this.number = number;
	}

	/**
	 * 식별자의 장점은 기능을 추가할 수 있다.
	 * ex) 1세대 시스템의 주문번호와 2세대 시스템의 주문번호를 구분할 때
	 */
	public boolean is2ndGeneration() {
		return number.startsWith("N");
	}
}
