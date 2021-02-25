package sample03_Advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
//advice after 이용
public class LoggingAfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("퇴실 체크");
		
	}

}
