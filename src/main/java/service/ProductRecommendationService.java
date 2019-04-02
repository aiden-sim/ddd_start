package service;

import domain.Product;
import domain.ProductId;

import java.util.List;

/**
 * 상품 추천 기능을 표현하는 도메인 서비스
 */
public interface ProductRecommendationService {
	List<Product> getRecommendationsOf(ProductId id);
}
