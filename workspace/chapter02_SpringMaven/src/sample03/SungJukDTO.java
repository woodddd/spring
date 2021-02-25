package sample03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

//@Component - 객체를 현재 클래스에서 생성하겠다.
@ComponentScan("spring.conf") //sping.conf 패키지 안에서 객체를 생성하겠다는 어노테이션.환경설정 파일(SpringConfiguration)에서 객체 생성을 하였다.
@Getter
//@Setter
public class SungJukDTO {
	//방법 1. @Setter를 이용했을 때의 초기화 방법.
//	private @Value("홍길동") String name;
//	private @Value("97") int kor;
//	private @Value("100")int eng;
//	private @Value("95")int math;
	
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	
	public SungJukDTO() {
		System.out.println("SungJukDTO 기본생성자");
	}
	
	@Autowired
	public void setName(@Value("홍길동")String name) {
		this.name = name;
	}
	@Autowired
	public void setKor(@Value("97")int kor) {
		this.kor = kor;
	}
	@Autowired
	public void setEng(@Value("100")int eng) {
		this.eng = eng;
	}
	@Autowired
	public void setMath(@Value("95")int math) {
		this.math = math;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	@Override
	public String toString() {
		return name+"\t"+kor+"\t"+eng+"\t"+math+"\t"+tot+"\t"+String.format("%.2f", avg);
	}
	
}
