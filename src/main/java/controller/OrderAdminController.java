package controller;

import domain.StartShippingRequest;
import exception.VersionConflictException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.StartShippingService;

@Controller
public class OrderAdminController {
	private StartShippingService startShippingService;

	@RequestMapping(value = "/startShipping", method = RequestMethod.POST)
	public String startShipping(StartShippingRequest startReq) {
		try {
			startShippingService.startShipping(startReq);
			return "shippingSTarted";
			// VersionConflictException은 이미 누군가가 애그리거트를 수정했다는 것을 의미
			// OptimisticLockingFailureException은 누군가가 거의 동시에 애그리거트를 수정했다는 의미
		} catch (OptimisticLockingFailureException | VersionConflictException ex) {
			// 트랜잭션 충돌
			return "startShippingTxConflic";
		}
	}
}
