package user.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.dao.UserDAOMybatis;
import user.service.UserDeleteService;
import user.service.UserInsertService;
import user.service.UserSelectService;
import user.service.UserService;
import user.service.UserUpdateService;

@Configuration
public class SpringConfiguration {
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("c##java");
		basicDataSource.setPassword("bit");
		basicDataSource.setMaxTotal(20);
		basicDataSource.setMaxIdle(3);
		return basicDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:spring/mybatis-config.xml") );
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		//setConfigLocation(Resource 타입의 자료형을 사용하는 매개변수를 이용해야한다. Resource는 자원을 뜻하는데
		//우리가 사용할 xml 경로를 지정할 때, Resource를 이용한다
		//그래서 우리는 ClassPathResource 클래스의 생성자를 이용하여 경로를 넣어줄 것이다.
		//ClassPathResource 는 클래스 패스를 지정해 줄 때 사용하므로 기존의 classpath: 를 생략한다.
		sqlSessionFactoryBean.setDataSource(dataSource());

		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
//	xml 에서 사용해준 <tx:annotation-driven transaction-manager="transactionManager"/>
//	를 사용하지 않아도 되는 이유는, return 에서 new 해서 트랜젝션 매니저를 생성한 경우, 객체가 생성될 때,
//	xml에 <context:component-scan base-package="user.conf"/> 를 지정해 주었으므로,
//	user.conf 패키지에서 트랜잭션 매니저 객체가 생성될 떄 스프링에 데이터가 알아서 올라간다.
	
	
	@Bean
	public UserDTO userDTO() {
		return new UserDTO();
	}
	
	@Bean //상속관게에서는 반환 자료형이 중요하지 않음 자식또는 부모
	public UserDAO userDAOMybatis() throws Exception {
		UserDAOMybatis userDAOMybatis = new UserDAOMybatis();
		userDAOMybatis.setSqlSession(sqlSession());
		
		return userDAOMybatis;
	}
	
	
	@Bean //자료형은 UserService 던 UserInsertService 이던 상관이 없음
	public UserService userInsertService() throws Exception {
		UserInsertService userInsertService = new UserInsertService();
		userInsertService.setUserDTO(userDTO());
		userInsertService.setUserDAO(userDAOMybatis());
		
		return userInsertService;
	}
	
	@Bean
	public UserService userSelectService() throws Exception {
		UserSelectService userSelectService = new UserSelectService();
		userSelectService.setUserDAO(userDAOMybatis());
		
		return userSelectService;
	}
	
	@Bean
	public UserService userUpdateService() throws Exception {
		UserUpdateService userUpdateService = new UserUpdateService();
		userUpdateService.setUserDAO(userDAOMybatis());
		
		return userUpdateService;
	}
	
	@Bean
	public UserService userDeleteService() throws Exception {
		UserDeleteService userDeleteService = new UserDeleteService();
		userDeleteService.setUserDAO(userDAOMybatis());
		
		return userDeleteService;
	}
	
}
