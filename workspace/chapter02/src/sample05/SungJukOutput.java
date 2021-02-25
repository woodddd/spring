package sample05;

import java.util.ArrayList;
import java.util.List;

public class SungJukOutput implements SungJuk {
	private List<SungJukDTO2> list;
	
	public void setList(List<SungJukDTO2> list) {
		this.list = list;
	}
	
	
	@Override
	public void execute() {
		
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		for(SungJukDTO2 sungJukDTO2: list) {
			System.out.println(sungJukDTO2);
		}
		
		
	}

}