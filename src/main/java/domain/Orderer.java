package domain;

import javax.persistence.*;

/**
 * 도메인 모델의 엔티티는 두 개 이상의 데이터가 개념적으로 하나인 경우 밸류 타입을 이용해서 표현
 */
@Embeddable
public class Orderer {

    // MemberId에 정의된 칼럼 이름을 변경하기 위해
    // @AttributeOverride 애노테이션 사용
    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    )
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;


    public Customer getCustomer() {
        return new Customer();
    }

    public String getCustomerId() {
        return "id";
    }
}
