package controller;

import dao.SomeDao;
import domain.EditRequest;
import lock.LockId;
import lock.LockManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.SomeEditService;

import javax.xml.crypto.Data;

@Controller
public class LockTestController {

	private LockManager lockManager;
	private SomeDao someDao;
	private SomeEditService someEditService;

	@RequestMapping("/some/edit/{id}")
	public String editForm(@PathVariable("id") String id, ModelMap model) {
		// 1. 오프라인 선점 잠금 시도
		LockId lockId = lockManager.tryLock("data", id);

		// 2. 기능 실행
		Data data = someDao.select(id);
		model.addAttribute("data", data);

		// 3. 잠금 해제에 사용할 LockId를 모델에 추가
		model.addAttribute("lockId", lockId);
		return "editForm";
	}

	@RequestMapping(value = "/some/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") String id, @ModelAttribute("editReq") EditRequest editReq, ModelMap model) {
		editReq.setId(id);

		// 1. 잠금 선정 확인
		// 잠금의 유효 시간이 지났으면 이미 다른 사용자가 잠금을 선점한다.
		// 잠금을 선점하지 않은 사용자가 기능을 실행했다면 기능 실행윽 막아야 한다.
		LockId lockId = new LockId(id);
		lockManager.checkLock(lockId);

		// 2. 기능 실행
		someEditService.edit(editReq);
		model.addAttribute("data", "test");

		// 3. 잠금 해제
		lockManager.releaseLock(lockId);

		return "editSuccess";
	}
}
