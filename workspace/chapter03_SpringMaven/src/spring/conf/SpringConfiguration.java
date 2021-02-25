package spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import sample01.LoggingAdvice;
import sample01.MessageBean;
import sample01.MessageBeanImpl;

@Configuration 
@EnableAspectJAutoProxy //xml의 <aop:aspectj-autoproxy/> 이 문장과 동일. aop 기능이 존재한다. 동일 기능이므로 xml 또는 설정파일 둘중 한군데에서만 써야한다.
public class SpringConfiguration {
	
	@Bean
	public MessageBean messageBeanImpl() {
		return new MessageBeanImpl();
	}
	
	@Bean
	public LoggingAdvice loggingAdvice() {
		return new LoggingAdvice();
	}
	
}
