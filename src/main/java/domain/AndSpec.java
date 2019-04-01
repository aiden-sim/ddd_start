package domain;

import jpasepc.Specification_old;

import java.util.Arrays;
import java.util.List;

/**
 * 여러 스펙을 하나의 스펙으로 만들어 리포지터리에 전달할 수 있다.
 */
public class AndSpec<T> implements Specification_old<T> {
	private List<Specification_old<T>> specs;

	public AndSpec(Specification_old<T>... specs) {
		this.specs = Arrays.asList(specs);
	}

	@Override public boolean isSatisfiedBy(T agg) {
		for (Specification_old<T> spec : specs) {
			if (!spec.isSatisfiedBy(agg)) {
				return false;
			}
		}
		return true;
	}
}
