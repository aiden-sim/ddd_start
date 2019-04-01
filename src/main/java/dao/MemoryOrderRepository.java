package dao;

import domain.Order;
import domain.OrderId;
import domain.OrderNo;
import domain.OrderNumber;
import jpasepc.Specification_old;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryOrderRepository implements OrderRepository {
	@Override public List<Order> findAll(Specification_old spec) {
		List<Order> allOrders = findAll();
		return allOrders.stream().filter(order -> spec.isSatisfiedBy(order)).collect(Collectors.toList());
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
}
