package domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderNo.class)
public abstract class OrderNo_ {

	public static volatile SingularAttribute<OrderNo, String> number;

}
