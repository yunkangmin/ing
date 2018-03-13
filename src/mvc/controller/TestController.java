package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.MailService;

@Controller
public class TestController {

	@Autowired
	MailService mailService;
	
	@RequestMapping("/test/email")
	public void emailTestHandler(@RequestParam String target) {
		boolean b=mailService.sendWelcomeMail(target);
		System.out.println("메일 전송 결과= "+b);
		
		//http://localhost/chap05/test/email?target=이메일 주소
	}
}
