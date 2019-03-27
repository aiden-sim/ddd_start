package dao;

import dto.OrderView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaOrderViewDao implements OrderViewDao {
	@PersistenceContext
	private EntityManager em;

	// JPQL 방식
	// 세타 조인으로 조회해서 한 번의 쿼리로 로딩한다. (쿼리가 복잡한 경우만 사용)
	@Override
	public List<OrderView> selectByOrderer(String ordererId) {
		String selectQuery =
				"select new dto.OrderView(o, m, p) " +
						"from Order o join o.orderLines ol, Member m, Product p " +
						"where o.orderer.memberId.id = :ordererId " +
						"and o.orderer.memberId = m.id " +
						"and index(ol) = 0 " +
						"and ol.productId = p.id " +
						"order by o.number.number desc";
		TypedQuery<OrderView> query =
				em.createQuery(selectQuery, OrderView.class);
		query.setParameter("ordererId", ordererId);
		return query.getResultList();
	}
}