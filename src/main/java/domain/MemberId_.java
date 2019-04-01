package domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MemberId.class)
public abstract class MemberId_ {
	public static volatile SingularAttribute<MemberId, String> id;
}

