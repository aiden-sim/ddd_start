package client;

import dao.ProductRepository;
import domain.Product;
import domain.ProductId;
import domain.RecommendationItem;
import service.ProductRecommendationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 인프라스트럭쳐
 */
public class RecSystemClient implements ProductRecommendationService {
	private ProductRepository productRepository;
	private ExternalRecClient externalRecClient;

	@Override public List<Product> getRecommendationsOf(ProductId id) {
		List<RecommendationItem> items = getRecItems(id.getValue());
		return toProducts(items);
	}

	private List<RecommendationItem> getRecItems(String itemId) {
		// externalRecClient는 외부 추천 시스템을 위한 클라이언트라고 가정
		return externalRecClient.getRecs(itemId);
	}

	private List<Product> toProducts(List<RecommendationItem> items) {
		return items.stream()
				.map(item -> toProductId(item.getItemId()))
				.map(prodId -> productRepository.findById(prodId))
				.collect(Collectors.toList());
	}

	private ProductId toProductId(String itemId) {
		return new ProductId(itemId);
	}
}
