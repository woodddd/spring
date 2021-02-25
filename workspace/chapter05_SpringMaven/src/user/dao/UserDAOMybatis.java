package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import user.bean.UserDTO;

/*기존 자바에서는 commit 동작을 알아서 해 주었다. 하지만 close 를 해주지 않기 때문에 @Transactional 를 사용하여 커밋과 클로즈 동작을 자동으로 하게 해준다.*/
@Setter
@ComponentScan("user.conf")
@Transactional /* xml에서 transactionManager 객체를 생성해주었으므로,@Transactional 어노테이션을 통하여 트랜잭션 매니저 객체를 가져와서 트랜잭션 처리에 대한 것을 직접 맡긴다. 만약 @Transactional 어노테이션을 사용해 주지 않았다면, 알아서 close 해주지 않는다. 트랜잭션commit,close 와같은 트랜잭션 처리에 대한 모든걸 다 처리해주겠다. */
public class UserDAOMybatis implements UserDAO {
	
	private SqlSession sqlSession;
	
	@Override
	public void write(UserDTO userDTO) {
		
		sqlSession.insert("userSQL.write", userDTO);
		
	}

	@Override
	public List<UserDTO> getUserList() {
		List<UserDTO> list = sqlSession.selectList("userSQL.getUserList");
		return list;
	}

	@Override
	public Map<String, Object> getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser",id);
	}

	@Override
	public int getUserCount(String id) {
		// TODO Auto-generated method stub 이건안할래
		return 0;
	}

	@Override
	public void modify(Map<String, Object> map) {
		sqlSession.update("userSQL.modify",map);
		
	}

	@Override
	public void delete(String id) {
		sqlSession.delete("userSQL.delete",id);
		
	}

}
