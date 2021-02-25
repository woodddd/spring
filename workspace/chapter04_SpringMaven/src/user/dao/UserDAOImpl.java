package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

//@Repository //디비와 연동되는 클래스는  객체를 생성할때 @Component 를 해도 되지만 동일 기능을 하는 Repository를 이용한다.
@ComponentScan("user.conf") //만약 세터를 사용하기 싫다면 객체를 생성할 떄, dataSource를 넣어주면 된다.
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO{

//	@Autowired
//	public void setDS(DataSource dataSource) {
//		setDataSource(dataSource); //부모에게 dataSource 를 전달하기 위해 부모메소드 호출
//	}

	@Override
	public void write(UserDTO userDTO) {
		String sql = "insert into usertable values(:name,:id,:pwd)"; 

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

//	@Override
//	public void userUpdate(Map<String,String> map) {
//		String sql = "update usertable set name=:name,id=:id,pwd=:pwd where id=:updateId";
//		
//		getNamedParameterJdbcTemplate().update(sql, map);
//		
//	}
//
//	@Override
//	public void userDelete(String id) {
//		String sql = "delete from usertable where id = ?";
//		getJdbcTemplate().update(sql,id);
//	}
	
	/*
	@Override
	public UserDTO getUser(String id) {
		String sql = "select * from usertable where id=:id";
		
		try {
			return getJdbcTemplate().queryForObject(
					sql, 
					new BeanPropertyRowMapper<UserDTO>(UserDTO.class),
					id);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	*/
	
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

