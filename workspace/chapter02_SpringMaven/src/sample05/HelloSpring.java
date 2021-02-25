package sample05;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloSpring {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = (HelloSpring)context.getBean("helloSpring");
		helloSpring.menu(context);
		System.out.println("프로그램 종료");
	}
	
	public void menu(ApplicationContext context) {
		Scanner scanner = new Scanner(System.in);
		int num;
		SungJuk sungJuk = null;
		
		while(true) {
			System.out.println("**************");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 끝");
			System.out.println("**************");
			
			System.out.print("번호 입력 : ");
			num = scanner.nextInt();
			
			System.out.println();
			
			if(num == 1) {
				sungJuk = (SungJuk)context.getBean("sungJukInput");
			}else if(num == 2) {
				sungJuk = (SungJuk)context.getBean("sungJukOutput");
			}else if(num == 3) {
				sungJuk = (SungJuk)context.getBean("sungJukModify");
			}else if(num == 4) {
				sungJuk = (SungJuk)context.getBean("sungJukDelete");
			}else if(num == 5) {
				break;
			}
			sungJuk.execute();
		}//while
	}
}
