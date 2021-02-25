package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.entity.Exam;
import spring.aop.entity.NewlecExam;
import spring.di.NewlecDiConfig;

public class Program {

	public static void main(String[] args) {
		
		ApplicationContext context=
				//new AnnotationConfigApplicationContext(NewlecDiConfig.class);
				new ClassPathXmlApplicationContext("spring/aop/setting1.xml");

		Exam proxy = (Exam) context.getBean("proxy");
		
		System.out.printf("total is %d\n",proxy.total());
		System.out.printf("avg is %f\n",proxy.avg());
		
		/*
		Exam exam = new NewlecExam(1,1,1,1);
		
		Exam proxy = (Exam) Proxy.newProxyInstance(NewlecExam.class.getClassLoader(),
				new Class[] {Exam.class},
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						long start = System.currentTimeMillis();
						
						Object result = method.invoke(exam, args);//(실제 업무 객체, 호출한 메소드가 가지고 있는 파라미터 를 args 가 가지고 있는데 그걸 받아온 것임)
						//result 는 실제 메소드 total, avg 가 실행되었을 때 반환하는 자료형을 받아 저장할 곳.
						
						
						
						long end = System.currentTimeMillis();
						
						String message = (end - start) + "ms 시간이 걸렸습니다.";
						System.out.println(message);
						
						return result;
					}
				});//Exam의 객체처럼 생성되는 가짜 proxy 객체를 생성하는 것. 
		//newProxyInstance 매개변수
		//(loader - 실제적인 클래스에 대한 정보
		//,interfaces - proxy 를 생성할 인터페이스. 만약 loader 에서 작성한 클래스가 복수의 인터페이스를 가지고 있다면 {} 안에 복수의 인터페이스의 클래스정보를 삽입한다.
		//,h) - 인터페이스를 구현한 클래스를 객체화 하는 작업.
*/
		
		
	}

}
