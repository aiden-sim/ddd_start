package service;

import dao.MemberRepository;
import domain.Member;
import exception.PasswordNotMatchException;

public class ChangePasswordService {

	private MemberRepository memeberRepository;

	public void changePassword(String memberId, String oldPw, String newPw) throws PasswordNotMatchException {
		Member member = memeberRepository.findById(memberId);
		checkMember();
		member.changePassword(oldPw, newPw);
	}

	private void checkMember() {

	}
}
