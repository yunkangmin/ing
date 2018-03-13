package mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller//디폴트가 앞에만 소문자임.
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

		//HttpSessionHandShakeInterceptor를 설정해두면
		//Spring이 이 메소드를 호출할때, 이 클라이언트가 사용중이던 HttpSession에 setAttribute
		//되어 있는 값들을 WebSocketSession에서 뽑아다 쓸 수 있게 넣어줌.
		//그러면서 추가로 "HTTP,SESSION.ID"라른 키로 사용중인 session id도 넣어주고,
		
		//spring이 중간에서 중재
		System.out.println("AlertController afterConnectionEstablished");
		Map<String,Object> map=session.getAttributes();
		System.out.println(map);
		String key =(String)session.getAttributes().get("HTTP.SESSION.ID");//Httpsession을 접근해서 정보를 얻어와야 함.그냥은 안되고..
		if (!sessions.containsKey(key)) {
			sessions.put(key, new ArrayList<>());
		}
		sessions.get(key).add(session);
		for(String k:sessions.keySet()) {
			
			int size=sessions.get(k).size();
			System.out.println("->"+k+"("+size+")");
		}
		
		
		
		/*for(WebSocketSession ws: sessions.get(key)) {
			
			ws.sendMessage(new TextMessage("이미 로그인 되어있습니다."));
			
		}*/
			
		

	}

	//sessionId-ws,ws,ws
	//sessoinId-ws,ws
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("AlertController afterConnectionClosed");
		String key=(String)session.getAttributes().get("HTTP.SESSION.ID");
		System.out.println(key);
		System.out.println(sessions);
		System.out.println(sessions.get(key));
		
		sessions.get(key).remove(session);
		if(sessions.get(key).isEmpty()) {
			sessions.remove(key);
			
		}

	}
}
