package sample02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalcMul implements Calc {
	private int x;
	private int y;
	
	@Autowired		//@Component를 썼을 때! DI 중에 생성자또는 Setter를 사용했으면 알아서 매핑해라.
	public void setX(@Value("25")int x) {
		this.x = x;
	}
	@Autowired
	public void setY(@Value("36")int y) {
		this.y = y;
	}
	
	@Override
	public void calculate() {
		System.out.println(x + "*" + y + "=" + (x*y));
	}

}
