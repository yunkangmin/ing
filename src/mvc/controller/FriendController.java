package mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
