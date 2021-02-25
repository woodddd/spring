package sample03;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageBean")
@Scope("prototype")
//어노테이션 방식.
//현재 클래스를 bean으로 잡아주세요. 그 후 MessageBeanKo 객체를 생성해주세요.
public class MessageBeanKo implements MessageBean{
	private int num;
	
	
	public MessageBeanKo() {
		System.out.println("MessageBeanKo 기본 생성자 실행");
	}
	public void sayHello(String name) {
		num++;
		System.out.println("num = " + num);
		System.out.println("안녕하세요 " + name + "!!");
	}
}
