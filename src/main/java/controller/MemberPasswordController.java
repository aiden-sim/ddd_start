package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member/changePassword")
public class MemberPasswordController {
	@RequestMapping(method = RequestMethod.POST)
	public String submit(HttpServletRequest request) {
		// 응용 서비스가 표현 영역에 대한 의존이 발생하면 안 됨!
		// changePasswordService.changePassword(request);

		return null;
	}
}