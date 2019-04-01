package domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Orderer.class)
public abstract class Orderer_ {
	public static volatile SingularAttribute<Orderer, String> name;
	public static volatile SingularAttribute<Orderer, MemberId> memberId;
}