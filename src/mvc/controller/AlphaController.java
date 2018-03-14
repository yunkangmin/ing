package mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.GreetService;

@Controller
public class AlphaController {

	@Autowired
	GreetService greetService;

	@RequestMapping({ "/index", "/" })
	public String alpha01Handle(Model model, HttpSession session) {
		

		if(session.getAttribute("logonId")==null) {
			model.addAttribute("ment", greetService.make());
			return "index";
		}else {
			return "logon";
		}
	
	}
	
	
}
