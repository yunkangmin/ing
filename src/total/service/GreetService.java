package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GreetService {

	@Autowired
	SqlSessionTemplate template;
	public String make() {
		String[] ments = "어서오세요,WELCOME,곤니치와".split(",");

		return ments[(int) (Math.random() * ments.length)];
	}
	public boolean join(Map map) throws Exception{
		
		return 1==template.insert("member.addJoin",map);
	}
}
