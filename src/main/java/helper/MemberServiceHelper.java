package helper;

import dao.MemberRepository;
import domain.Member;
import exception.NoMemberException;

// 각 응용 서비스에서 공통되는 로직을 별도 클래스로 구현
public class MemberServiceHelper {
	private static Member findExistingMemeber(MemberRepository repo, String memberId) throws NoMemberException {
		Member member = repo.findById(memberId);
		if (member == null) {
			throw new NoMemberException(memberId);
		}
		return member;
	}
}
