package sample04_Advisor;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LoggingBeforeAdvice implements MethodBeforeAdvice{ //자바 내부의 인터페이스를 이용

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {//콜백메소드
		System.out.println("입실 체크");
		
		
		
	}

}
