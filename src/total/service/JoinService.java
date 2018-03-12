package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

	@Autowired
	SqlSessionTemplate template;

	public boolean join(Map map) throws Exception {

		return 1 == template.insert("member.addJoin", map);
	}
	public Map loginp(Map map){
		
		return  template.selectOne("member.getByIdmailAndPassword", map);
	}
	public Map auth(String s){
		
		return  template.selectOne("member.getById", s);
	}

}
