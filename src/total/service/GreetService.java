package total.service;

import org.springframework.stereotype.Service;

@Service
public class GreetService {

	public String make() {
		String[] ments = "어서오세요,WELCOME,곤니치와".split(",");

		return ments[(int) (Math.random() * ments.length)];
	}
}
