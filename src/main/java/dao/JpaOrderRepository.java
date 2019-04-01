package dao;

import domain.*;
import jpa.JpaQueryUtils;
import jpasepc.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class JpaOrderRepository implements OrderRepository {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 애그리거트를 검색
	 */
	@Override
	public Order findById(OrderNo id) {
		return entityManager.find(Order.class, id);
	}

	/**
	 * 애그리거트를 저장
	 */
	@Override
	public void save(Order order) {
		entityManager.persist(order);

	}

	/**
	 * JPQL 방식
	 */
	@Override
	public List<Order> findByOrdererId(String ordererId, int startRow, int fetchSize) {
		TypedQuery<Order> query = entityManager.createQuery(
				"select  o from Order o " +
						"where o.orderer.memberId.id = :ordererId " +
						"order by o.number.number desc", Order.class);
		query.setParameter("ordererId", ordererId);
		// 페이징 처리
		query.setFirstResult(startRow);
		query.setMaxResults(fetchSize);
		return query.getResultList();
	}

	@Override
	public Order findByNumber(OrderNumber number) {
		return null;
	}

	@Override
	public void delete(Order order) {
		entityManager.remove(order);
	}

	@Override
	public Order findById(OrderId id) {
		return null;
	}

	/**
	 * 스펙을 이용해서 구현
	 */
	@Override public List<Order> findAll(Specification<Order> spec, String... orders) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = cb.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		Predicate predicate = spec.toPredicate(root, cb);
		criteriaQuery.where(predicate);
		if (orders.length > 0) {
			criteriaQuery.orderBy(JpaQueryUtils.toJpaOrders(root, cb, orders));
		}

		TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override public List<Order> findAll() {
		return null;
	}

	@Override public Long countsAll() {
		TypedQuery<Long> query = entityManager.createQuery(
				"select count(o) from Order o", Long.class);
		return query.getSingleResult();
	}

	@Override public Long counts(Specification<Order> spec) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		criteriaQuery.select(cb.count(root)).where(spec.toPredicate(root, cb));
		TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
	}
}
