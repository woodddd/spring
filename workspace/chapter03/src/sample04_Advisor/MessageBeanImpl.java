package sample04_Advisor;

public class MessageBeanImpl implements MessageBean {
	
	@Override
	public void study() {
		System.out.println("입실");
		System.out.println("수업시간에 공부한다");//핵심코드
		System.out.println("퇴실");
	}

	@Override
	public String game() {
		System.out.println("입실");
		System.out.println("수업시간에 몰래 게임한다");//핵심코드
		System.out.println("퇴실");
		return "바부팅이";
	}

	@Override
	public void lesson() {
		System.out.println("컴퓨터 수업을 듣는다");
		
	}
}
