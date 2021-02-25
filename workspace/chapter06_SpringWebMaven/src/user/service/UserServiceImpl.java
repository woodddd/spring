package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service(value="userService") //서비스에 관련된 클래스로 서비스로직을 처리한다.라는의미
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;


	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
		
	}


	@Override
	public String checkId(String id) {
		UserDTO userDTO = userDAO.checkId(id);
		
		if(userDTO == null) {
			return "사용 가능";
		}else {
			return "사용 불가능";	
		}
	}


	@Override
	public List<UserDTO> getUserList() {
		return userDAO.getUserList();
	}


	@Override
	public UserDTO getUser(String id) {
		return userDAO.getUser(id);
	}


	@Override
	public void modify(UserDTO userDTO) {
		userDAO.modify(userDTO);
	}


	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}


	@Override
	public List<UserDTO> search(Map<String, String> map) {
		return userDAO.search(map);
	}

}


















