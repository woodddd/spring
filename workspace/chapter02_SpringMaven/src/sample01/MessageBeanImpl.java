package sample01;

import org.springframework.beans.factory.annotation.Value;//@Value("") 는 lombok이 아닌 스프링의 것임!
import org.springframework.stereotype.Component;

@Component
public class MessageBeanImpl implements MessageBean {
	private @Value("딸기") String fruit; //@Value("") 는 lombok이 아닌 스프링의 것임!
	private @Value("5000") int cost; //@Value 는 객체가 생성될 때, 값을 부여하는 것임. private int cost=5000; 과 동일한기능.
	private @Value("3")int qty;
	
	
	
	@Override
	public void sayHello() {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
		
	}

	@Override
	public void sayHello(String fruit, int cost) {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
		
	}

	@Override
	public void sayHello(String fruit, int cost, int qty) {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
		
	}

}
