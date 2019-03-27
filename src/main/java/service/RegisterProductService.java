package service;

import dao.ProductRepository;
import domain.Product;
import domain.ProductId;
import exception.StoreBlockedException;

public class RegisterProductService {
	private AccountRepository accountRepository;
	private ProductRepository productRepository;

	public ProductId registerNewProduct(NewProductRequest req) throws StoreBlockedException {
		Store account = accountRepository.findStoreById(req.getStoreId());
		checkNull(account);
		ProductId id = productRepository.nextId();
		// 팩토리를 이용해서 new Product을 사용하지 않고 변경 했다.
		Product product = account.createProduct(id);
		productRepository.save(product);
		return id;
	}

	private void checkNull(Store account) {

	}
}
