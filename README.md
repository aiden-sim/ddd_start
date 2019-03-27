## 도메인 모델 패턴
- 표현 (Controller) => 응용(Service) => 도메인(domain) => 인프라스트럭쳐 (DB)

## 도메인 영역의 주요 구성요소
- 엔티티(Entitiy) : 고육의 식별자를 갖는 객체 
- 밸류(Value) : 고유의 식별자를 갖지 않는 객체로 주로 개념적인 하나인 도메인 객체의 속성을 표현. 엔티티의 속성이나 다른 밸류 타입의 속성으로 사용된다.
- 애그리거트(Aggregate) : 관련된 엔티티와 밸류 객체를 개념적으로 하나로 묶은 것
- 리포지터리(Repository) : 도메인 모델의 영속성을 처리한다.
- 도메인 서비스(Domain Service) : 특정 엔티티에 속하지 않은 도메인 로직을 제공한다. 여러 엔티티와 밸류를 필요할 경우 도메인 서비스에서 로직을 구현

## 도메인 모델 엔티티 vs DB 모델 엔티티
- 도메인 모델의 엔티티는 데이터와 함께 도메인 기능을 함께 제공
- 도메인 모델의 엔티티는 두 개 이상의 데이터가 개념적으로 하나인 경우 밸류 타입을 이용해서 표현
  - 예를 들어 RDMS에 주문자를 표현하려면 Order(주문) 테이블에 orderer_name, orderer_email을 같이 넣거나 테이블을 분리해서
  order (no, orderer_name, orderer_email), order_orderer (order_no, orderer_name, orderer_email) 중복되게 저장 해야 된다.

## JPA에서 Repository Interface 사용 이유
- Interface 자체는 고수준 이기 때문에 Service(고수준)에서 Repositroy(고수준)을 통해 RepositoryImpl(저수준)에 접근한다.
  - 이렇게 하면 인ㅌ프라스트럭처에 의존하면 생기는 2가지 문제 (테스트의 어려움과 기능 확장의 어려움)이 해소된다.
  
## 요청 처리 흐름
![spring](https://user-images.githubusercontent.com/7076334/55076338-7f469180-50d8-11e9-8cad-7e051a12d0d0.jpg)

## 생각해볼점
- 실제 프로젝트 내에서 Service에서 외부 API통신을 하거나 인프라스트럭처 용도를 사용할 때는 인터페이스(고수준)을 두어서 유연하게 만들자.
- 하지만 예외도 있다. 인프라스트럭처에 대한 의존을 일부 도메인에 넣은 코드 (보통 JPA에서 많이 사용하는)
  - ex) @Entity @Table(name = "TBL_ORDER") public class Order {...}





