package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Category {
	// 다른 애그리거트에 대한 1:N 연관
	private Set<Product> products;

	// 페이지네이션
	public List<Product> getProducts(int page, int size) {
		List<Product> sortedProducts = sortById(products);
		return sortedProducts.subList((page - 1) * size, page * size);
	}

	private List<Product> sortById(Set<Product> products) {
		return new ArrayList<>();
	}

	public CategoryId getId() {
		return new CategoryId();
	}
}
