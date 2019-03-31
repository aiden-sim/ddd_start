package domain;

import jpa.MoneyConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @EmbeddedId
    private ProductId id;
    private String name;

    // 부분적으로 적용할 때는 Convert 사용
    @Convert(converter = MoneyConverter.class)
    private Money price;
    private String detail;

    // Image는 Entity이지만 밸류 타입으로 Product에 완전 의존한다.
    // 리스트에서 Image 객체를 제거하면 DB에서 함께 삭제 되도록 orphanRemoval 사용
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER) // 루트를 구할 때, 연관된 구성요소를 DB에서 함께 읽어온다.
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private List<Image> images = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_option",
            joinColumns = @JoinColumn(name = "product_id"))
    @OrderColumn(name = "list_idx")
    private List<Option> options = new ArrayList<>();

    public void removeOption(int optIdx) {
        // 실제 컬렉션에 접근할 때 로딩
        this.options.remove(optIdx);
    }

    public void changeImages(List<Image> newImages) {
        // onToMany 관계에서 clear는 삭제 과정이 효율적이지 않을 수 있다.
        // delete 시, 개별 건건 처리한다. 성능 이슈 될 수 있다.
        // 하지만 Embeddable 타입에서는 한 번의 delete 쿼리로 삭제 처리
        images.clear();
        images.addAll(newImages);
    }

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
