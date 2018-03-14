package mvc.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import total.service.GreetService;
import total.service.JoinService;

@Controller

public class JoinController {

	@Autowired
	JoinService joinService;
	@Autowired
	GreetService greetService;

	@RequestMapping(path = "/join", method = RequestMethod.GET)
	public String joinGetHandle(Model model) {

		model.addAttribute("ment", greetService.make());
		return "join";
	}

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map map, Model model, HttpSession session) {

		System.out.println(map);
		boolean b = false;
		try {
			b = joinService.join(map);
			if(b) {
				session.setAttribute("logonId", map.get("id"));
			/*	
				List<WebSocketSession> s=sessions.get(session.getId());
				for(WebSocketSession ws:s) {
					ws.sendMessage(new TextMessage(""));
				}
				*/
				String sid=session.getId();
				System.out.println(sid);
				return "redirect:/";
			}
			throw new Exception();
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("data", map);
			model.addAttribute("err", "계정생성에서 문제가 있었습니다.");
			return "join";
		}


	}

	@RequestMapping("/login")
	public String loginHandle() {

		return "login";
	}

	@RequestMapping(path = "/loginp")
	public String loginpHandle(@RequestParam Map<String, String> map, HttpSession session,Model model) {

		Map<String, Object> result = joinService.loginp(map);
		if (result != null) {
			session.setAttribute("logonId", map.get("idmail"));
			return "redirect:/";
		} else {
			model.addAttribute("err", "logon failed");
			return "login";
		}

	}
	@RequestMapping("/chatroom")
	public String chatRoomHandle(HttpSession session) {
		
		Map<String, Object> result = joinService.auth((String)session.getAttribute("logonId"));
		int compare=((BigDecimal)result.get("LV")).intValue();
		
		if(0==compare){
			
			return "auth";
			
			
		}else if(1==compare){
			
			return "chatroom";
			
		}else{
			return "";
			
		}
		
		
		
	}

}

/*


## git을 이용한 버전 관리 ##

target폴더 우클릭 team ignore눌러서 감시대상에서 제외시키기

Git 저장소에 프로젝트 로케이션을 설정 후,
 MVC가 작동하는 걸 확인 했으면, 이걸 최초 git에 버전 등록을 할꺼임.

프로젝트 우클릭 team -> commit -> 목록 내린후-> commit

프로젝트 우클릭 ->team->
ignore를 잘못했을시 .gitignore에서 목록을 지운다.

버전돌아가기 
프로젝트 우클릭 team -> show in history -> 프로젝트 우클릭 ->reset ->hard

#이 곳에 회원가입을 만들려고 함.#
 git의 branch? master(최초등록시)

회원가입 처리용 컨트롤러인 JoinController를 설계해서 , mapping을 "/join"잡아서 이 경로 왔을때.
id/email/password를 입력하는 view로 넘기게 처리하고,
index용 뷰에서 여기로 a href를 걸어두자.

버전 커밋
#이제 작업을 branch를 만들어서 회원가입을 완성할꺼임#
통째로 들어내야하는 작업, 긴 작업
현재 상태와 똑같은 프로젝트를 하나 더 만들어내는 효과임.(실험적인 작업)
프로젝트 우클릭 >> team >> switch to>> new branch >> "join-branch"

+oracle db에 회원정보 저장할 테이블을 하나 만들고, 이전꺼 말고

table member
id varchar2(30)/ email varchar2(30) not null / password varchar2(3) not null
level number(1,0) not null,


id pk  
email unique

스프링 빈 설정 파일에 db작업 설정 추가 
datasource / sqlsessionfactory / sql sessiontemplat
mapper
여기까지만 설정 해서 commit 브랜치 버전으로 등록됨.

회원정보 등록하는 일 처리할 서비스 객체를 설계,
 pubilc boolean -------------(Map map){


}

"/join" ,post방식으로 들어올때
파라미터 얻어서 , 위에 만든 서비스 객체 이용해서 작업처리하고
결과에 따라서 성공하면, 세션에 "logon"이름으로 가입시 사용했던 id심어버리고 
"redirect:/"
실패하면, 뷰를 다시 join을 사용하는데, 이때 원인이 나오게끔 해두면 됨.
실패 원인을 모델에 세팅해서 join으로 보내고, join용 jsp를 어떤 특정 데이터가 있냐 없냐에 따라서 에러메세지를 출력

#브랜치 작업이 완료 됬다면, 이걸 마스터 브랜치에 적용을 시켜야 함.#

서버끄고
switch to  >>master branch

team >> merge 타겟브랜치 /
껏다키기
"join-branch-2"브랜치 만들기


GitHub ??
로컬에 있는 깃 저장소를 원격으로 백업을 해둘수 있는데, 이걸 github라고 부름.
처음가입시 메일을 보낸다. 메일 확인 누른상태에서 쓴다.
join/plan

git원격저장소
git을 이용해서 하던 프로젝트만가능

start a project >>  복사
이클립스에서 master로 바꾸고 team push branch >>깃헙확인
store security 해제(개인컴퓨터일때만 체크)
local-> hub-> local
       push       pull
최초 push하기 위해선 ,hub에서 원격 repository를 만들어야 하고
pull을 하기 위해선 ,로컬에 연결된rep가 있어야 하니까 (clone부터)
클론이 된 이후부터는 push pull만 하면 된다.
=====================================================

이메일 인증을 받아야만 사용자가 우리가 제공하는 서비스를 사용할 수 있게 할꺼임.

로그인 이후의 화면에 채팅방개설이라는 메뉴를 만들어서 링크를 걸고,
이걸 받아주는 컨트롤러에서 사용자 계정 lv(0이메일 인증 아직 안함/1 이메일 인증값)값에 따라 다른 뷰로 유도를 해주면 됨.

lv1같은 경우는 jsp로 보내서 채팅방개설에 필요한 정보 입력 화면
lv 0같은 경우는 인증받아야 된다는 경로로 리다이렉트

인증을 안했을때 화면
인증을 한 화면
new로 가서 redirect시킴
같은 메뉴에 인증을 받냐 안받냐에 따라 다른 화면으로 유도
0,1,2
자기파트브랜치를 만들어서 push
*/

