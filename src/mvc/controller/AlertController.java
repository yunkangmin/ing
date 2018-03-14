package mvc.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import total.model.WebSocketMap;

@Controller//디폴트가 앞에만 소문자임.
public class AlertController extends TextWebSocketHandler {
	/*
	 * 웹소켓 커넥션이 열릴때, 세션을 키로 해서 묶어서 관리를 하려고 함.
	 * 
	 * 
	 */


	/*@Autowired
	Map<String, List<WebSocketSession>> sessions;*/
	
	@Autowired
	WebSocketMap sessions;
	

	/*@PostConstruct
	public void init() {
		sessions = new HashMap<>();

	}
*/
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

/*
##스프링의 웹소켓 서버#
스프링은 별도의 웹소켓 서버를 설치하지 않아도 웹소켓기능을 사용할 수 있게 지원함.
웹소켓api을 사용하게 되면 서버에서 먼저 알림을 보내는게 가능해짐.
웹특성상 상대방이 요청을 해야 응답을 보낸다.


서버측에서 클라이언트에게 먼저 메시지를 전달

웹소켓이라는 걸 사용하지 않고, 서버측에서 최신데이터를 뿌려줄려면,(전송/실시간 알림등등)
자바스크립트를 이용해서 최신목록을 뿌려주는 컨트롤러로 setInterval로 설정해서 ajax로 주기적으로 읽어가게끔 유도를 해야됨.이렇게도 구현은 가능하지만 서버측에 과부하가 걸림.

그래서, 웹 어플리케이션에 서버측에서 먼저 데이터들을 보내줘야되는 성격의 어플리케이션을 구현할때는 웹소켓서버를 하나 더 연동해서 프로그램 구현을 해야됨.Spring은 WAS를 가지고도 웹소켓서버기능(HTML5부터 추가된 기술)을 구현할 수 있게 해두었음.(우리가 먼저 응답을 보낼수 있다.물론 사용자들도 보낼수 있긴하다.)

스프링 웹소켓기능을 사용하려면,라이브러리를 받아야 함.

mvnrepository.com에서 spring websocket검색
spring websocket클릭 4.3.14.버전

ws-study브랜치만들기

네임스페이스에서 websocket체크

커밋하고

마스터로 바꾸고

ws-alert로 바꾸기

같은세션의 클라이언트는 묶어놈
같은브라우저야지만 같은세션종류이다.

pom.xml에 spring websocket또 추가하기

네임스페이스에서 websocket체크*/
