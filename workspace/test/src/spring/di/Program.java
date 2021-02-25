package spring.di;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;

public class Program {

	public static void main(String[] args) {

		/* 스프링에게 ㅈ시하는 방법으로 코드를 변경
		Exam exam = new NewlecExam();
		//ExamConsole console = new InlineExamConsole(exam);//DI - exam 주입
		ExamConsole console = new GridExamConsole();
		
		console.setExam(exam);
		*/
		
		ApplicationContext context=
				new AnnotationConfigApplicationContext(NewlecDiConfig.class);
//				new ClassPathXmlApplicationContext("spring/di/setting1.xml");
				
		
//		Exam exam = context.getBean(Exam.class);
//		System.out.println(exam);
		
		ExamConsole console = (ExamConsole) context.getBean("console"); //이름으로 찾는 경우
		//ExamConsole console = context.getBean(ExamConsole.class); //자료형으로 찾는 경우
		console.print();
		
		//List<Exam> exams = (List<Exam>) context.getBean("exams");
		//exams.add(new NewlecExam(1,1,1,1));
		
		//for(Exam e : exams) {
		//	System.out.println(e);
		//}
	}

}
