package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {
	
	@Autowired
	//@Qualifier("exam2")
	private Exam exam;
	
	public InlineExamConsole() {
		System.out.println("constructor");
	}
	
//	@Qualifier("exam2")
//	@Autowired
//	public InlineExamConsole(@Qualifier("exam1")Exam exam) {
//		System.out.println("overloaded constructor");
//		this.exam = exam;
//	}
	
	
	@Override
	public void setExam(Exam exam) {
		System.out.println("setter");
		this.exam = exam;
		
	}
	
	@Override
	public void print() {
		if(exam == null) {
			System.out.printf("total is %d, avg is %f\n", 0, 0.0);
		}else {
			System.out.printf("total is %d, avg is %f\n", exam.total(), exam.avg());
		}

	}


}
