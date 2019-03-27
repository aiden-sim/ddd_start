package service;

import dao.CategoryRepository;
import dao.ProductRepository;
import domain.Category;
import domain.Page;
import domain.Product;

import java.util.List;

public class ProductListService {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;

	public Page<Product> getProductOfCategory(Long categoryId, int page, int size) {
		Category category = categoryRepository.findById(categoryId);
		checkCategory(category);
		List<Product> products = productRepository.findByCategoryId(category.getId(), page, size);
		long totalCount = productRepository.countsByCategoryId(category.getId());
		return new Page(page, size, totalCount, products);

	}

	private void checkCategory(Category category) {

	}
}
