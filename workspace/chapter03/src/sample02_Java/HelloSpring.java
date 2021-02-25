package sample02_Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloSpring {

   public static void main(String[] args) {
      MessageBean messageBean = new MessageBeanImpl(); //부모=자식
      
      MessageBean proxy = (MessageBean) Proxy.newProxyInstance(
            MessageBeanImpl.class.getClassLoader(),//타겟클래스
            new Class[] {MessageBean.class},//인터페이스
            new InvocationHandler() {//핸들러
               
               @Override
               public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { //invoke 는 콜백메소드.
                  System.out.println("입실 체크"); //공통
                  
                  Object ob = method.invoke(messageBean, args); //핵심코드 호출
                  
                  System.out.println("퇴실 체크"); //공통
                  
                  return ob;
               }
            });
      
      proxy.study(); //호출
      System.out.println("------------------");
      System.out.println("결과 = " + proxy.game());

   }

}
