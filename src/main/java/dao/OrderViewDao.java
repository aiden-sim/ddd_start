package dao;

import dto.OrderView;

import java.util.List;

public interface OrderViewDao {
	List<OrderView> selectByOrderer(String ordererId);
}
