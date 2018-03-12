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
