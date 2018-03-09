package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JoinService {

	@Autowired
	SqlSessionTemplate template;
public boolean join(Map map) throws Exception{
		
		return 1==template.insert("member.addJoin",map);
	}

}
