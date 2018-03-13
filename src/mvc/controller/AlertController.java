package mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class AlertController extends TextWebSocketHandler {
	/*
	 * 웹소켓 커넥션이 열릴때, 세션을 키로 해서 묶어서 관리를 하려고 함.
	 * 
	 * 
	 */

	Map<String, List<WebSocketSession>> sessions;

	@PostConstruct
	public void init() {
		sessions = new HashMap<>();

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		
		// 어떤 세션인가(어떤 HttpSession인가)
		// 세션아이디로 접근 (브라우저마다 할당되므로)

		System.out.println("AlertController afterConnectionEstablished");
		String key = "";//Httpsession을 접근해서 정보를 얻어와야 함.그냥은 안되고..
		if (sessions.containsKey(key)) {
			sessions.put(key, new ArrayList<>());

			sessions.get(key).add(session);
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("AlertController afterConnectionClosed");

	}
}
