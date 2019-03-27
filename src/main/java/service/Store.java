package service;

import domain.Member;
import domain.Product;
import domain.ProductId;
import exception.StoreBlockedException;

/**
 * Product을 생성하는 팩토리 역할
 */
public class Store extends Member {

	public Product createProduct(ProductId newProductId) throws StoreBlockedException {
		if (isBlocked())
			throw new StoreBlockedException();
		return new Product(newProductId, getId());
	}

	public boolean isBlocked() {
		return false;
	}

	public String getId() {
		return null;
	}
}
