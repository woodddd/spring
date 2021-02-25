package sample04;

import org.springframework.stereotype.Component;

@Component
//만약 객체 이름이 calcMul같이 소문자로만 작성하는 객체를 생성한다면, 이정도는 Spring이 알아서 해주기 때문에 작성하지 않아도된다,
public class CalcMul implements Calc{

	@Override
	public void calculate(int x, int y) {
		System.out.println(x + " * " +  y + " = " + (x*y) );
		
	}

}
