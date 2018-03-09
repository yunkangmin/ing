package mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.GreetService;
import total.service.JoinService;

@Controller

public class JoinController {

	@Autowired
	JoinService joinService;
	@Autowired
	GreetService greetService;
	
	
	@RequestMapping(path="/join",method=RequestMethod.GET)
	public String joinGetHandle(Model model) {
		
		model.addAttribute("ment", greetService.make());
		return "join";
	}
	@RequestMapping(path="/join",method=RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map map ,Model model,HttpSession session) {
		
		System.out.println(map);
		boolean b=false;
		try {
			b = joinService.join(map);
			
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(!b) {
			model.addAttribute("data",map); 
			model.addAttribute("err", "계정생성에서 문제가 있었습니다.");
			return "join";
			}
		
		else {  
			session.setAttribute("logonId", map.get("id"));
			return "redirect:/"; }
		
	}

}
