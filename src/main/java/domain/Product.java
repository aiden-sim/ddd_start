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

    @Column(name = "WIDTH")
    private String width;

    public Length getWidth() {
        // DB 칼럼 값을 실제 프로퍼티 타입으로 변환
        return new Width(width);
    }

    void setWidth(Length width) {
        // 실제 프로퍼티 타입을 DB 칼럼 값으로 변환
        this.width = width.toString();
    }

    public Product(ProductId id, String id1) {

    }
}
