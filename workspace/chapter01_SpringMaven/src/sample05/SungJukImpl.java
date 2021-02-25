package sample05;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SungJukImpl implements SungJuk{
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double avg;
	
	public SungJukImpl() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("이름 입력: ");
		name = scanner.next();
		
		System.out.print("국어 입력: ");
		kor = scanner.nextInt();
		
		System.out.print("영어 입력: ");
		eng = scanner.nextInt();
		
		System.out.print("수학 입력: ");
		math = scanner.nextInt();
	}

	@Override
	public void calc() {
		total = kor + eng + math;
		avg = total/3.0;
	}

	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println("이름"+"\t"+"국어"+"\t"+"영어"+"\t"+"수학"+"\t"+"총점"+"\t"+"평균");
		System.out.println(name+"\t"+kor+"\t"+eng+"\t"+math+"\t"+total+"\t"+df.format(avg));
		
	}
	
}
