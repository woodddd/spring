package spring.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample03.SungJukDTO;
import sample03.SungJukImpl;
import sample05.SungJukDTO2;


@Configuration 
public class SpringConfiguration {
	
//	@Bean 
//	public SungJukDTO sungJukDTO() {
//		return new SungJukDTO();
//	}
	
	@Bean(name="sungJukDTO")
	public SungJukDTO getSungJukDTO() {
		return new SungJukDTO();
	}
	
	@Bean(name="sungJukImpl")
	public SungJukImpl getSungJukImpl() {
		return new SungJukImpl(); 
	}
	
//	@Bean
//	public SungJukInput sungJukInput() {
//		return new SungJukInput();
//	}
	
	@Bean
	public List<SungJukDTO2> list(){
		return new ArrayList<SungJukDTO2>();
	}
	
}
