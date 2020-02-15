package book.chapter4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    @Column(name = "WIDTH")
    private String width;

    public Length getWidth() {
        return new Width(width);       // DB 컬럼 값을 실제 프로퍼티 타입으로 변환
    }

    void setWidth(Length width) {
        this.width = width.toString(); // 실제 프로퍼티 타입을 DB 컬럼 값으로 변환
    }


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private List<Image> images = new ArrayList<>();

}
