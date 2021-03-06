package total.service;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	JavaMailSender mailSender;

	public boolean sendWelcomeMail(String target) {
		MimeMessage message=mailSender.createMimeMessage();
		                                             
		try {
			
			//받는 사람                                                                                  //이메일을 이객체로 바꾼다.
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			
			//보내는 사람 -google 서버같은 경우는 이 설정을 무시함.
			message.setFrom(new InternetAddress("administrator@spring.io"));
			
			//제목
			message.setSubject("[SpringIO] 가입을 축하드립니다.");
			
			String content="가입을 축하드립니다.\n사용에 불편하신 점이 있으면 고객센터에 글을 남겨주세요.";
			
			//content+="<a href=\"http://localhost\">사이트 둘러보기</a>";
			
			
			
			message.setContent(content,"text/plain;charset=utf-8");
			//content 설저을 text/html;charset=utf-8"이라고 보내면 HTML로  보낼수도 있다.
			//String으로 짜야 한다.
			
			//메일 발송
			mailSender.send(message);
			//smtp로 우회해서 ㅎ발송
			return true;
			
			
			     //사용자 이메일주소가 없을때에러
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		
	}

}





/*

java mail api #

mvnrepository.com에서 라이브러리를 연동받자
java api(compat)

자바 메일 api를 사용법만 알고 있다면, 이건 웹프로젝트(spring)이 아니더라도 발송은 가능함.
spring에서는 메일 api를 쓰기 쉽게 지원.

Spring-context support

==============================================================================

필요한 라이브러리들은 준비가 끝났고, 메일을 외부로 전송하기 위해선 smtp 서버가 필요함.
자체 smtp서버를 구축하지 못하는 상황에서는 네이버 smtp혹은 구글 smtp서버를 이용해서 작업을 함.

구글 계정이 필요함. smtp접속 설정에 , 계정명이랑 비번이 들어가니까.
스프링 설정 파일에 온다음에 메일전송용 빈을 등록하고 필요한 곳에서 땡겨다 쓰면 됨.
javaMailsenderimpl

※Property로 설정해야되는게 정보가 더 있는데, 애는 필수고 , 디폴트 설정 그대로 사용
===========================================================================
서비스구축은 완료됬음.

total.controller에 
 TestController클래스를 만들어서,

외부로그인을 막아놓은 상태
*/