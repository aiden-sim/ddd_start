package service;

import dao.MemberRepository;
import dao.Notifier;
import domain.Member;
import exception.NoMemberException;
import exception.PasswordNotMatchException;

public class MemberService {
	private MemberRepository memberRepository;
	private Notifier notifier;

	public void changePassword(String memberId, String currentPw, String newPw) throws PasswordNotMatchException, NoMemberException {
		Member member = findExistingMemeber(memberId);
		member.changePassword(currentPw, newPw);
	}

	public void initializePassword(String memberId) throws NoMemberException {
		Member member = findExistingMemeber(memberId);
		String newPassword = member.initializePassword();
		notifier.notifyNewPassword(member, newPassword);
	}

	public void leave(String memeberId, String curPw) throws NoMemberException {
		Member member = findExistingMemeber(memeberId);
		member.leave();
	}

	private Member findExistingMemeber(String memberId) throws NoMemberException {
		Member member = memberRepository.findById(memberId);
		if (member == null) {
			throw new NoMemberException(memberId);
		}
		return member;
	}
}
