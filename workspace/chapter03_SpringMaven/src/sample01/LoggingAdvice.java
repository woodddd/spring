package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//공통관심사항, 공통모듈, 부가기능, Aspect
@Aspect //나는 공통 모듈입니다. 이 어노테이션을 제거하게 되면 공통모듈을 못찾기 때문에 공통모듈이 나오지 않음
@ComponentScan("spring.conf")
public class LoggingAdvice {
	
	@Before("execution(public void sample01.MessageBeanImpl.*Before(..))")
	//() 안에 있는 메소드가 실행될 때(pointcut-joinpoint 메소드 중 부가기능을 적용할 것들을 선별), before 에 삽입합니다.
	public void beforeTrace() {
		System.out.println("before trace...");
	}
	
	@After("execution(public * *.*.*After(..))")
	public void afterTrace() {
		System.out.println("after trace...");
	}
	
	@Around("execution(public * *.*.*Print(..))")
	public void trace(ProceedingJoinPoint joinpoint) throws Throwable{
		//공통
		String methodName = joinpoint.getSignature().toShortString();
		System.out.println("메소드 = " + methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		
		//핵심관심사항 호출 
		Object ob = joinpoint.proceed(); //showPrint/viewPrint 메소드를 호출하고 끝나면 다시 공통코드로 넘어감.
		System.out.println("return 값 = " + ob);
		//공통
		sw.stop();
		System.out.println("처리 시간 = " + sw.getTotalTimeMillis()/1000 + "초");
	}
}
