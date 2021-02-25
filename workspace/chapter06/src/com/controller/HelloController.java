package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping(value="/hello.do", method=RequestMethod.GET)//get방식으로 주소가 /hello 로 들어오면 이 메소드를 처리해라.
	public ModelAndView hello() { //사용자가 만든 콜백메소드.
		ModelAndView mav = new ModelAndView();
		mav.addObject("result","Hello Spring!!");
		mav.setViewName("hello"); //주의사항! 뒤에 절대 hello.jsp , hello.html 과같이 확장자를 붙이면 안된다. 뷰의 이름만 쓰는 것이다.
		
		return mav;
	}
	
}
/*
만약 URL
http://localhost:8080/chapter06/hello 요청이 들어오면 (web.xml 에서 설정을 /* 로했기 때문에 알 수 있다.)
	
DispatcherServlet 으로 요청을 받는다.

handlelerMapping 이 @Controller로 표시된 클래스에 연결한다.

@Controller가 있는 클래스에서 요청된 (/Hello) 를 찾아서 메소드를 호출한다.(콜백메소드)

@Controller
public class Test{
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public ModelAndView 메소드명() {
	
	}
}

만약 중복되는 URL 이 존재한다면 에러를 발생한다. 그래서 그럴 때에 사용되는게 namespace이다.

@RequestMapping(value="/hello", method=RequestMethod.POST)
	public ModelAndView hello() { 
		ModelAndView mav = new ModelAndView();
	}
	
	메소드 방식이 달라도 URL 이 같으면 에러를 발생한다.
*/