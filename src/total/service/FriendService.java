package total.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

@Service
public class FriendService {
	@Autowired
	Gson gson;
	
	@Autowired
	SqlSessionTemplate template;
	
	Map<String, List<WebSocketSession>> map;
	
	@PostConstruct
	public void init() {
		map = new HashMap<>();
	}
	
	public boolean addWebSocket(String key, WebSocketSession session) {
		if(!map.containsKey(key)) {
			map.put(key, new ArrayList<>());
		}
		return map.get(key).add(session);
	}
	
	public boolean removeWebSocket(String key, WebSocketSession session) {
		boolean r = map.get(key).remove(session);
		if(map.get(key).isEmpty()) {
			map.remove(key);
		}
		return r;
	}
	
	
	public void sendRequestMessage(String one, String other) throws IOException {
		if(map.containsKey(other)) {
			Map msg = new LinkedHashMap<>();
				msg.put("mode", "fr_req");
				msg.put("from", one);
			for(WebSocketSession ws : map.get(other)) {
				ws.sendMessage(new TextMessage(gson.toJson(msg)));
			}
		}
	}

	public void sendAcceptedMessage(String one, String other) throws IOException {

	}
	
	public void sendDeniedMessage(String one, String other) throws IOException {

	}
	public List<Map> readSearch(String search){
		
		
		return template.selectList("member.readSearch","%"+search+"%");
		
		
	}
}


/*http://

친구추가 #
쌍방 허락에 의해서 허용되는 
A->B 신청 B가 허락을 해야 친구,B가 취소를 하면 (삭제)
둘중에 하나라도 취소를 하면 친구가 안됨.


친구 요청이 발생하게 되면 DB에 데이터는 집어넣고 상대방이 접속중이면
 실시간 알림을 받을 수 있게 구현.
친구 요청건에 변화 발생시 보낸 사람이 접속중이면,
 본인이 접속중이면 실시간 알림을 받을 수 있게 구현.

DB Instance DDL#

create table friend(

no number(4,0) not null,
one varchar2(30) not null,
other varchar2(30) not null,
status number(1,0) not null,  --0:처리안됨/ 1.:처리됨
reqdate date not null,

constraint friend_rule01 primary key(no),
constraint friend_rule02 unique(one,other), ---한사람이 
constraint friend_rule03 foreign key(other) references member(id)


);

create sequence friend_seq starts with 1;


필요한 맵퍼파일 서비스 컨트롤러 구축해서 
로그인한 사람들이 보는 메뉴에 친구 추가를 넣고

이기능은 로그인을 한 유저들만 쓰게

1.mapper파일부터
 데이터 insert statement :새로운 친구요청 데이터 등록
 데이터 select statement : other=?이고 status=0인 데이터를 확보
 데이터 update statement : 특정 키에 해당하는 status를 변경
 데이터 delete statement: 특정 키에 해당하는 데이터를 삭제
2. 친구 관리용 서비스 객체 설계
 필요한 데이터를 Controller에서 넘겨받고, DB처리하고 결과를 컨트롤러에게 알려주게

3. 요청받아주는 컨트롤러 설계
새로운 친구 추가 요청(2개)하나는 상대방을 입력하는 폼으로 유도하는 매핑 / 이 폼에서 날라온 요청을 처리할 맵핑= )

친구 신청들어온것 확인하는 쪽 맵핑 1개
   (허락/ 거절 이런 버튼 혹은 링크를 만들어두면 될꺼고)
허락요청 처리하는 맵핑1개
거절요청 처리하는 맵핑 1개 

4.웹소켓사용해서
로그인한 사람들만 볼 수 있는 뷰 영역에 특정경로로  websocket연결시키고
    onmessage처리하는 스크립트

이거 받아주는 웹소켓핸들러 작성
   (발생하는 웹소켓들을 로그인한 ID로 묶어주기만 하면 됨 / 연결해제 되면 삭제는 해야될꺼고)

5.3번에 만들어둔 서비스 객체에서 db처리만 하지말고  모아둔 웹소켓들 와이어링받아서,
   상대방에 해당하는 소켓이 있을때 개한테 메세지 날리게 추가 작업
6.onmessage처리하는 스크립트에 날라온 메세지에 따라서 친구요청이 들어왔습니다.
친구 요청이 수락되었습니다. 친구요청이 거절되었습니다라고 alert 혹은 html로 확인할수 있게
마무리
=========================================================================


요청이 들어온 사람 같은 경우는, (신청이 몇건 들어와 있습니다.)


ws-alert
커밋(세션ID를 통한 웹소켓 그룹제어)
push

switch to
ws-study 
push

ws-friend잡기 */

