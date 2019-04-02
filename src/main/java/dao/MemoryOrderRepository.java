package dao;

import domain.Order;
import domain.OrderId;
import domain.OrderNo;
import domain.OrderNumber;
import jpasepc.Specification;

import java.util.ArrayList;
import java.util.List;

public class MemoryOrderRepository implements OrderRepository {
	@Override public List<Order> findAll(Specification<Order> spec, String... orders) {
		return null;
	}

	@Override public List<Order> findAll() {
		return new ArrayList<>();
	}

	@Override public Order findById(OrderNo no) {
		return null;
	}

	@Override public void save(Order order) {

	}

	@Override public List<Order> findByOrdererId(String ordererId, int startRow, int size) {
		return null;
	}

	@Override public Order findByNumber(OrderNumber number) {
		return null;
	}

	@Override public void delete(Order order) {

	}

	@Override public Order findById(OrderId id) {
		return null;
	}

	@Override public Long countsAll() {
		return null;
	}

	@Override public Long counts(Specification<Order> spec) {
		return null;
	}

	@Override public OrderNo nextId() {
		return null;
	}

	@Override public Order findByIdOptimisticLockMode(OrderNo id) {
		return null;
	}
}
