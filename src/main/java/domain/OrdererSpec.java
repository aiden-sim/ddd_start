package domain;

import jpasepc.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public class OrdererSpec implements Specification<Order> {
	private String ordererId;

	public OrdererSpec(String ordererId) {
		this.ordererId = ordererId;
	}

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaBuilder cb) {
		return (Predicate) cb.equal(root.get(Order_.orderer)
				.get(Orderer_.memberId).get(MemberId_.id), ordererId);

	}
}