package sample01;
//joinpoint
public interface MessageBean {
	//이 둘에겐 코드가 삽입되지만
	//pointcut
	public void showPrintBefore();
	public void viewPrintBefore();
	
	public void showPrintAfter();
	public void viewPrintAfter();
	
	public String showPrint();
	public void viewPrint();
	
	
	//이 메소드에겐 코드가 삽입되지 않는다.
	public void display();
}
