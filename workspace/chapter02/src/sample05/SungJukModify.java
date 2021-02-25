package sample05;

import java.util.List;
import java.util.Scanner;

public class SungJukModify implements SungJuk {
	private List<SungJukDTO2> list;
	
	public void setList(List<SungJukDTO2> list) {
		this.list = list;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		int sw = 0;
		
		System.out.print("수정할 이름 입력 : ");
		String name = scanner.next();
		
		for(int i = 0; i < list.size(); i++) {
			if(name.equals(list.get(i).getName())) {
				sw ++;
				
				System.out.print("국어점수 입력 : ");
				list.get(i).setKor(scanner.nextInt());
				System.out.print("영어점수 입력 : ");
				list.get(i).setEng(scanner.nextInt());
				System.out.print("수학점수 입력 : ");
				list.get(i).setMath(scanner.nextInt());
				
				list.get(i).setTot(list.get(i).getKor() + list.get(i).getEng() + list.get(i).getMath());
				list.get(i).setAvg(list.get(i).getTot()/3.0);
				break;
			}
		}//for
		
		if(sw == 0) {
			System.out.println("찾고자하는 이름이 없습니다.");
		}else {
			System.out.println(name + "님의 데이터를 수정하였습니다.");
		}
	}

}
