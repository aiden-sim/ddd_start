package dao;

import domain.Order;
import domain.OrderId;
import domain.OrderNo;
import domain.OrderNumber;
import jpasepc.Specification;

import java.util.List;

public interface OrderRepository {
	Order findById(OrderNo no);

	void save(Order order);

	List<Order> findByOrdererId(String ordererId, int startRow, int size);

	Order findByNumber(OrderNumber number);

	void delete(Order order);

	Order findById(OrderId id);

	List<Order> findAll(Specification<Order> spec, String... orders);

	List<Order> findAll();

	Long countsAll();

	Long counts(Specification<Order> spec);

	OrderNo nextId();

	Order findByIdOptimisticLockMode(OrderNo id);
}
