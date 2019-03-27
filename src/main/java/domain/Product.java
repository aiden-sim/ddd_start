package domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
	@EmbeddedId
	private ProductId id;

	@ElementCollection
	@CollectionTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"))
	private Set<CategoryId> categoryIds;

	public Product(ProductId id, String id1) {

	}
}
