package jpasepc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

/**
 * 모든 데이터를 메모리에 가지고 있어야 되기 때문에 데이터가 커지면 이슈가 된다.
 */
public interface Specification<T> {
	Predicate toPredicate(Root<T> root, CriteriaBuilder cb);
}
