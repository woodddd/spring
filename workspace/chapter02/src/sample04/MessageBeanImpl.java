package sample04;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
public class MessageBeanImpl implements MessageBean {
	//@NonNull
	private String name;
	//@Setter
	private String phone;
	//@Setter
	private Outputter outputter; //파일로 출력
	
	public MessageBeanImpl(String name) {
		System.out.println("MessageBeanImpl(String name)");
		this.name = name;
	}
	
	
	public void setPhone(String phone) {
		System.out.println("setPhone(String phone)");
		
		this.phone = phone;
	}

	public void setOutputter(Outputter outputter) {
		System.out.println("setOutputter(Outputter outputter)");
		this.outputter = outputter;
	}



	@Override
	public void helloCall() {
		System.out.println("helloCall()");
		outputter.output("이름="+name + "\t 핸드폰=" + phone);
	}

}
