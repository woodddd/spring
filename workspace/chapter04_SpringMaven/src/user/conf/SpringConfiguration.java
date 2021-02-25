package user.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;

@Configuration
public class SpringConfiguration {

	@Bean
	public BasicDataSource dataSource(){
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
	public UserDAO userDAOImpl() {
		UserDAOImpl userDAOImpl = new UserDAOImpl();
      	userDAOImpl.setDataSource(dataSource());
      	return userDAOImpl;
   }
	
//	@Bean
//	public UserDAO userDAOImpl() {
//      	return new UserDAOImpl();
//   }
}
