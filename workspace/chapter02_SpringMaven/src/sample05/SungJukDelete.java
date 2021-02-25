package sample05;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SungJukDelete implements SungJuk {
	@Autowired
	@Qualifier("list")
	private List<SungJukDTO2> list;
	

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요 : ");
		String name = scan.next();
		
		int sw = 0;
		Iterator<SungJukDTO2> it = list.iterator();
		while(it.hasNext()) {
			if(it.next().getName().equals(name)) {
				it.remove();
				sw = 1;
				
				System.out.println(name+"님의 데이터를 삭제하였습니다");
			}
		}//while
		
		if(sw==0)
			System.out.println("찾고자 하는 이름이 없습니다");
	}

}
