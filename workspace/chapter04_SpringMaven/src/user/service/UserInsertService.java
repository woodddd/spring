package user.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service //똑같이 객체를 생성하는 @Component랑 같은 어노테이션이지만,db를 불러오는, 데이터를 처리하는 서비스 객체라는것을 명시적으로 알려줌
public class UserInsertService implements UserService {
	
	@Autowired
	private UserDTO userDTO;
	@Autowired
	private UserDAO userDAO;
	
//	UserDAO는 부모클래스이지만 쿼리파이어를 지정해주지 않아도 되는 이유는 자식클래스가 UserDAOImpl한개밖에 없기 때문에
//	따로 쿼리파이어를 지정해줄 필요가 없는 것이다.

	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		//데이터
		System.out.println("이름 입력: ");
		String name = scan.next();
		System.out.println("아이디 입력: ");
		String id = scan.next();
		System.out.println("비밀번호 입력: ");
		String pwd = scan.next();
		
		userDTO.setName(name);
		userDTO.setId(id);
		userDTO.setPwd(pwd);
		
		//DB
		userDAO.write(userDTO);
		
		//응답
		System.out.println("데이터를 저장하였습니다");

	}

}
