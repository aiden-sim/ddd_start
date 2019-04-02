package controller;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ChangeShippingRequest;
import service.ChangeShippingService;

@Controller
public class OrderController {
	private ChangeShippingService changeShippingService;

	@RequestMapping(value = "/changeShipping", method = RequestMethod.POST)
	public String changeShipping(ChangeShippingRequest changeReq) {
		try {
			changeShippingService.changeShipping(changeReq);
			return "changeShippingSuccess";
		} catch (OptimisticLockingFailureException ex) {
			// 누군가 먼저 같은 주문 애그리거트를 수정했다.
			return "changeShippingTxConflic";
		}
	}

}
