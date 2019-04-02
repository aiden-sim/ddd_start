package domain;

import domain.*;

import javax.persistence.Embedded;
import java.util.List;

/**
 * 도메인 서비스
 */
public class DiscountCalculationService {

	@Embedded
	private Orderer orderer;

	public Money calculateDiscountAmounts(
			List<OrderLine> orderLines,
			List<Coupon> coupons,
			MemberGrade grade) {
		Money couponDiscount =
				coupons.stream()
						.map(coupon -> calculateDiscount(coupon))
						.reduce(new Money(0), (v1, v2) -> v1.add(v2));

		Money membershipDiscount =
				calculateDiscount(orderer.getMember().getGrade());
		return couponDiscount.add(membershipDiscount);
	}

	private Money calculateDiscount(Coupon coupon) {
		return null;
	}

	private Money calculateDiscount(MemberGrade grade) {
		return null;
	}
}
