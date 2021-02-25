package sample05;

import java.util.List;
import java.util.Scanner;



public class SungJukInput implements SungJuk {
	private SungJukDTO2 sungJukDTO2;
	private List<SungJukDTO2> list;
	
	
	
	public void setSungJukDTO2(SungJukDTO2 sungJukDTO2) {
		this.sungJukDTO2 = sungJukDTO2;
	}

	public void setList(List<SungJukDTO2> list) {
		this.list = list;
	}


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
		
		System.out.println(sungJukDTO2.getName() + "님의 데이터를 입력하였습니다.");
		
	}

}
