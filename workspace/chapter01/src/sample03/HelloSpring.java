package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		//스프링 설정 파일을 가져온다(읽는다.)
		//ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//ApplicationContext 는 interface라 직접적으로 new할수 없어서  FileSystemXmlApplicationContext를 통하여 xml을 읽어온다.
		
		MessageBean messageBean1 = (MessageBean) context.getBean("messageBean", MessageBean.class);  //xml에서 MessageBeanKo클래스의 messageBean 아이디를 가진 객체의 주소값을 읽어와라.
		//주소로 담는 객체의 타입은 부모 타입으로 받아와야 한다.(다형성) 결합도를 낮춘다.(부모=자식)
		messageBean1.sayHello("Spring");
		System.out.println();
		
		MessageBean messageBean2 = (MessageBean) context.getBean("messageBean");
		messageBean2.sayHello("Spring");
		
		MessageBean messageBean3 = (MessageBean) context.getBean("mb");
		messageBean3.sayHello("Spring");
		
		((AbstractApplicationContext) context).close();
	}

}
