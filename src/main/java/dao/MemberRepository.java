package dao;

import domain.Member;

public class MemberRepository {
	public Member findById(String memberId) {
		return new Member();
	}
}
