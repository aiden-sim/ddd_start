package lock;

import org.springframework.security.authentication.LockedException;

public interface LockManager {
	// 1) 잠금 선점 시도
	LockId tryLock(String type, String id) throws LockedException;

	// 2) 잠금 확인
	void checkLock(LockId lockId) throws LockedException;

	// 3) 잠금 해제
	void releaseLock(LockId lockId) throws LockedException;

	// 4) 락 유효 시간 연장
	void extendsLockExpiration(LockId lockId, long inc) throws LockedException;
}
