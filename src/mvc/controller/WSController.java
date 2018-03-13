package mvc.controller;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

/*
 * ws통신을 제어할 용도의 컨트롤러
 * 
 * - WebSocketHandler(인터페이스)목적에 맞게 개조해서 사용
 * - 목적에 맞는 WebSocketHandler를 extends걸어서 사용
 *      1.TextWebSocketHandler(문자)/  2.BinaryWebSocketHandler(파일)
 *      
 *   WebSocket Handler의 매핑은, spring 설정파일에 ..
 * 
 */
@Controller("wsController") // scan으로 등록되면 디폴트이름이 클래스이름으로 등록됨.
public class WSController extends TextWebSocketHandler {

	Set<WebSocketSession> wsSessions;
	Map<String, Object> map;
	Gson gson;

	@PostConstruct // init-method 자동으로 등록시 어노테이션으로 등록
	public void init() {
		wsSessions = new LinkedHashSet<>();
		map = new HashMap<>();
		gson = new Gson();
	}

	// 연결
	@Override // 클라이언트가 웹소켓 연결되었을때.. //자바스크립트로 연결
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished.. " + session);

		// inetSocketAddress
		// System.out.println(session.getRemoteAddress().getHostName());//상대방아이피
		// inetaddress
		System.out.println(session.getRemoteAddress().getAddress().getHostAddress());

		wsSessions.add(session);

		map.put("cnt", wsSessions.size());
		map.put("info", "connected "+session.getRemoteAddress().getAddress().getHostAddress());

		for (WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map))); // 기존 session과 다른 객체
			// 상대방과 연결되어있는 session
			// 메시지를 보낼때 TextMessage객체를 만들엇 보낸다.

		}

	}

	// 메시지 보낼때
	@Override // 클라이언트에서 메세지가 들어올때. 쌍방향통신이기때문에 양측에서 먼저 메시지를 보낼수 있다.클라이언트는 자바스크립트로 메세지를 보낸다.
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		System.out.println("handleTextMessage.. " + session + " / " + message);

	}

	// 연결해제
	@Override // 페이지 전환 ,리플레쉬하면 웹소켓이 닫힌다.
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		System.out.println("afterConnectionClosed.. " + session);

		wsSessions.remove(session);
		map.put("cnt",wsSessions.size());
		map.put("info", "disconnectd "+session.getRemoteAddress().getAddress().getHostAddress());

		for (WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map))); // 기존 session과 다른 객체
			// 상대방과 연결되어있는 session
			// 메시지를 보낼때 TextMessage객체를 만들엇 보낸다.

		}

	}

}
