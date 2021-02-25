package sample02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalcAdd implements Calc {
	private int x;
	private int y;
	
	public CalcAdd(@Value("25")int x,@Value("36") int y) {//롬복을 사용하는 것이 아닌 spring 의 Value 어노테이션을 사용하는 것.
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void calculate() {
		System.out.println(x + "+" + y + "=" + (x+y));

	}

}
