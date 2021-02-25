package sample03_Advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext03.xml");
		MessageBean messageBean = (MessageBean) context.getBean("proxy");
		messageBean.study();
		System.out.println("------------------");
		System.out.println("결과 = " + messageBean.game());
	}

}
