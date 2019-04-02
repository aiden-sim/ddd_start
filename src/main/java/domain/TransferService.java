package domain;

/**
 * 도메인 서비스
 */
public class TransferService {

	public void transfer(Account fromAcc, Account toAcc, Money amounts) {
		fromAcc.withdraw(amounts);
		toAcc.credit(amounts);
	}
}
