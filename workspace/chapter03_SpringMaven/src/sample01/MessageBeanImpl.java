package sample01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//핵심관심사항, Target

//타겟클래스 있는 모든 함수들을 조인포인트(JoinPoint) 라고 한다.
//그리고 그 중 showPrintBefore()와, viewPrintBefore() 에만 코드를 삽입할 것이므로 이 둘은 pointCut이 된다.
//MessageBeanImpl 의 메소드 호출 시 MessageBeanImpl 의 함수가 삽입되는 기능을 한다. 
@ComponentScan("spring.conf")
public class MessageBeanImpl implements MessageBean {
	private String str;
	
	@Autowired
	public void setStr(@Value("Have a nice day!!") String str) {
		this.str = str;
	}

	@Override
	public void showPrintBefore() {
//		System.out.println("before trace..."); 중복 코드를 aspect(부가기능) 가 대신해준다.
		System.out.println("showPrintBefore 메시지 = " + str);
		
	}

	@Override
	public void viewPrintBefore() {
//		System.out.println("before trace...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("viewPrintBefore 메시지 = " + str);
	}
	
	
	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메시지 = " + str);
		
	}
	
	@Override
	public void viewPrintAfter() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("viewPrintAfter 메시지 = " + str);
		
	}
	
	@Override
	public String showPrint() {
		System.out.println("showPrint 메시지 = " + str);
		
		return "스프링!!";
	}
	
	@Override
	public void viewPrint() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("viewPrint 메시지 = " + str);
		
	}

	@Override
	public void display() {
		System.out.println("display 메시지 = " + str);
	}


}
