package user.service;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.Setter;
import user.dao.UserDAO;

@Service
public class UserDeleteService implements UserService {
	@Autowired
	@Setter
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("삭제할 아이디 입력 >> ");
		String id = scan.next();
		
		Map<String, Object> map = userDAO.getUser(id);
		if(map==null) {
			System.out.println("찾고자 하는 아이디 없음");
			return;
		}
		
		userDAO.delete(id);
		
		System.out.println("데이터를 삭제하였습니다");

	}


}
