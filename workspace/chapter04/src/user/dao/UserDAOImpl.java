package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import user.bean.UserDTO;

/*
// 내가 직접 JdbcTemplate bean으로 생성하지 않겠다.
//  
//템프릿의 객체를 직접 생성하여 템플릿을 가져와 사용한 방법.
//여기서 문제점은 객체를 매번 DAO를 만들 때 마다 받아와야하므로 매번 private JdbcTemplate jdbcTemplate;
//	를 써 주어야 한다.
//	
//	그래서 앞으로는 내가 직접 JdbcTemplate bean으로 생성하지 않는다.
public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void write(UserDTO userDTO) {
		String sql = "insert into usertable values(?,?,?)";
		
		jdbcTemplate.update(sql,userDTO.getName(),userDTO.getId(),userDTO.getPwd());
		
		 //기존에 사용하던 close 며 getConnection 이며 이러한 동작을 할 필요가 없다. 스프링의 템플릿 내부에 있는
		 //aop 가 getConnection close 와 같은 공통기능을 자동으로 실행해 주기 때문에 더이상 우리가 직접적으로
		 //모든 코드를 써줄 필요가 없다. 다 해준다.
	}

}
*/

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO{

	@Override
	public void write(UserDTO userDTO) {
		//String sql = "insert into usertable values(?,?,?)";
		//getJdbcTemplate().update(sql,userDTO.getName(),userDTO.getId(),userDTO.getPwd());
		//getJdbcTemplate() 는 JdbcDaoSupport 에서 제공하고 있는 메소드이다.
		//한글 파일에서 NamedParameterJdbcDaoSupport 를 검색해 보면 설명이 쓰여있다.
		String sql = "insert into usertable values(:name,:id,:pwd)"; 
		//물음표를 대신하여 직접 named 를 적어주는 방법이 있다. 이 방법을 NamedParameter 라고한다.
		//NamedParameter 는 Map을 이용하여 데이터를 주고받는다.
		
		//위의 방법은 템플릿을 이용한 방법이고, 아래의 방법은 네임드파라미터를 이용한 방법이다.
		Map<String,String> map = new HashMap<String,String>();
		map.put("name",userDTO.getName());
		map.put("id",userDTO.getId());
		map.put("pwd",userDTO.getPwd());
		getNamedParameterJdbcTemplate().update(sql,map);//네임드 방법은 무조건 map을 이용해야 한다!!!
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "select * from usertable";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class)); 
		//row(행 - 줄) rowMapper(인터페이스)
		//UserDTO.class - sql 결과로 나온 한 행당, UerDTO 와 이름을 매핑하여 DTO 에 값을 알아서 넣어줌
	}
	
	
	public Map<String, Object> getUser(String id){
		String sql = "select * from usertable where id=:id";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
	
		try {
			map = getNamedParameterJdbcTemplate().queryForMap(sql, map);
		
		}catch(EmptyResultDataAccessException ex){
		   	map = null;
		}
		
		return map;
	}

	@Override
	public int getUserCount(String id) {
		String sql = "select count(*) from usertable where id=:id";
		return getJdbcTemplate().queryForObject(
				sql, 
				Integer.class,
				id);
	}

	@Override
	public void modify(Map<String, Object> map) {
		String sql = "update usertable set name=:name, pwd=:pwd where id=:id";
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(String id) {
		String sql = "delete from usertable where id=:id";
		getJdbcTemplate().update(sql, id);
	}
}

