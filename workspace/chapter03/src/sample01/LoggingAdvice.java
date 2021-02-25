package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//공통관심사항, 공통모듈, 부가기능, Aspect
public class LoggingAdvice {
	public void beforeTrace() {
		System.out.println("before trace...");
	}
	public void afterTrace() {
		System.out.println("after trace...");
	}
	
	public void trace(ProceedingJoinPoint joinpoint) throws Throwable{
		//공통
		String methodName = joinpoint.getSignature().toShortString();
		System.out.println("메소드 = " + methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		
		//핵심관심사항 호출 
		joinpoint.proceed(); //showPrint/viewPrint 메소드를 호출하고 끝나면 다시 공통코드로 넘어감.
		
		//공통
		sw.stop();
		System.out.println("처리 시간 = " + sw.getTotalTimeMillis()/1000 + "초");
	}
}
