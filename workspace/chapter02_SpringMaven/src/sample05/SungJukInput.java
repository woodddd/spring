package sample05;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@ComponentScan("spring.conf")
@Component
@Scope("prototype")
public class SungJukInput implements SungJuk {
	@Autowired
	private SungJukDTO2 sungJukDTO2;
	
	@Autowired
	@Qualifier("list")
	private List<SungJukDTO2> list; 
	//만약 얘를 private ArrayList<SungJukDTO2> arraylist; 로 작성하는 경우 어레이리스트는 자바에서 제공하는 클래스이기 때문에 @Bean 을 따로 작성할 필요없이 알아서 만들어준다.
	
	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("이름 입력 : ");
		sungJukDTO2.setName(scanner.next());
		System.out.print("국어점수 입력 : ");
		sungJukDTO2.setKor(scanner.nextInt());
		System.out.print("영어점수 입력 : ");
		sungJukDTO2.setEng(scanner.nextInt());
		System.out.print("수학점수 입력 : ");
		sungJukDTO2.setMath(scanner.nextInt());
		
		sungJukDTO2.setTot(sungJukDTO2.getKor()+sungJukDTO2.getEng()+sungJukDTO2.getMath());
		sungJukDTO2.setAvg(sungJukDTO2.getTot()/3.0);
		
		list.add(sungJukDTO2);
		System.out.println("list " + list);
		
		System.out.println(sungJukDTO2.getName() + "님의 데이터를 입력하였습니다.");
		
	}

}
