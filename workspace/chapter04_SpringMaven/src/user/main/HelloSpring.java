package user.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.service.UserInsertService;
import user.service.UserService;

public class HelloSpring {

	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
		int num;
		
		while(true) {
			System.out.println();
			System.out.println("********************");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 종료");
			System.out.println("********************");
			System.out.println("번호 입력: ");
			num = scan.nextInt();
			if(num == 1) {
				userService = (UserService) context.getBean("userInsertService"); 
			}else if(num == 2) {
				userService = (UserService) context.getBean("userSelectService");
			}else if(num == 3) {
				userService = (UserService) context.getBean("userUpdateService");
			}else if(num == 4) {
				userService = (UserService) context.getBean("userDeleteService");
			}else if(num == 5) {
				break;
			}
			userService.execute();
		}//while
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		HelloSpring helloSpring = new HelloSpring(); //얘는 변하지 않으므로 new 를 해줘도 문제없음.
		helloSpring.menu(context);
		System.out.println("프로그램 종료");

	}

}
