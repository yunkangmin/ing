package mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import total.service.FriendService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	@Autowired
	FriendService friendService;

	@RequestMapping(path = "/request", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String freindRequestHandle(@RequestParam String target, HttpSession session) {
		String one = (String) session.getAttribute("logonId");
		try {
			friendService.sendRequestMessage(one, target);
			return "true";
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}

	}
	@RequestMapping(path="/search",method = RequestMethod.GET)
	public String friendSearchGetHandler(Model model) {
	
		return "search";
		
	}
	@RequestMapping(path="/search",method = RequestMethod.POST)
	public String friendSearchPostHandler(@RequestParam String search,Model model) {
		List<Map> list=friendService.readSearch(search);
		
		model.addAttribute("list",list);
		return "search";
		
	}
}
