package domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * JPA 정적 메타 모델
 */
@StaticMetamodel(Order.class)
public abstract class Order_ {
	public static volatile SingularAttribute<Order, OrderNo> number;
	public static volatile ListAttribute<Order, OrderLine> orderLines;
	public static volatile SingularAttribute<Order, Orderer> orderer;
	public static volatile SingularAttribute<Order, ShippingInfo> shippingInfo;
	public static volatile SingularAttribute<Order, OrderState> state;

}
