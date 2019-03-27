package dao;

import domain.CategoryId;
import domain.Product;
import domain.ProductId;

import java.util.List;

public interface ProductRepository {
	void save(Product product);

	Product findById(ProductId id);

	void remove(Product product);

	List<Product> findAll();

	List<Product> findByCategoryId(CategoryId categoryId, int page, int size);

	long countsByCategoryId(CategoryId categoryId);

	ProductId nextId();
}