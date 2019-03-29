package dao;

import domain.Order;
import domain.OrderId;
import domain.OrderNo;
import domain.OrderNumber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
}
