package domain;

import exception.PasswordNotMatchException;
import org.springframework.util.StringUtils;

public class Member {
	private Password password;

	public void changePassword(String oldPw, String newPw) throws PasswordNotMatchException {
		if (!matchPassword(oldPw)) {
			throw new PasswordNotMatchException();
		}
		setPassword(newPw);
	}

	public boolean matchPassword(String pwd) {
		return password.match(pwd);
	}

	private void setPassword(String newPw) {
		if (StringUtils.isEmpty(newPw)) {
			throw new IllegalArgumentException("no new password");
		}
		this.password = new Password(newPw);
	}

	public String initializePassword() {
		return "";
	}

	public void leave() {

	}
}
