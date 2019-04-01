package jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 문자열을 Order로 변경해 주는 JpaQueryUtils 보조 클래스
 */
public class JpaQueryUtils {
	public static <T> List toJpaOrders(Root<T> root, CriteriaBuilder cb, String... orders) {
		if (orders == null || orders.length == 0) {
			return Collections.emptyList();
		}

		return Arrays.stream(orders)
				.map(orderStr -> toJpaOrder(root, cb, orderStr))
				.collect(Collectors.toList());
	}

	private static <T> Order toJpaOrder(Root<T> root, CriteriaBuilder cb, String orderStr) {
		String[] orderClause = orderStr.split(" ");
		boolean ascending = true;
		if (orderClause.length == 2 && orderClause[1].equalsIgnoreCase("desc")) {
			ascending = false;
		}
		String[] paths = orderClause[0].split("\\.");
		Path<Object> path = root.get(paths[0]);
		for (int i = 1; i < paths.length; i++) {
			path = path.get(paths[i]);
		}
		return ascending ? cb.asc(path) : cb.desc(path);
	}
}
