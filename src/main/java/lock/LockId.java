package lock;

/**
 * 잠금을 구하면 잠금을 해제하거나, 잠금이 유효한지 검사하거나, 잠금의 유효 시간을 늘릴 때 LockId를 사용한다.
 */
public class LockId {
	private String value;

	public LockId(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
