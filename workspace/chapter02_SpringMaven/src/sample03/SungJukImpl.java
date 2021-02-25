package sample03;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


//@Component
@ComponentScan("spring.conf")
public class SungJukImpl implements SungJuk {
	@Autowired
	private SungJukDTO sungJukDTO;
	
//   public SungJukImpl(SungJukDTO sungJukDTO) {
//      this.sungJukDTO = sungJukDTO;
//   }
	
	public SungJukImpl() {
		System.out.println("기본 생성");
	}

   @Override
   public void calcTot() {
      sungJukDTO.setTot(sungJukDTO.getKor()+sungJukDTO.getEng()+sungJukDTO.getMath());
   }

   @Override
   public void calcAvg() {
      sungJukDTO.setAvg(sungJukDTO.getTot() / 3.0);
   }

   @Override
   public void display() {
      System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
      System.out.println(sungJukDTO);
   }

   @Override
   public void modify() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("이름 입력 : ");
      sungJukDTO.setName(scanner.next());
      System.out.print("국어 입력 : ");
      sungJukDTO.setKor(scanner.nextInt());
      System.out.print("영어 입력 : ");
      sungJukDTO.setEng(scanner.nextInt());
      System.out.print("수학 입력 : ");
      sungJukDTO.setMath(scanner.nextInt());
   }

}