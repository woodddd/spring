package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.SumDTO;

@Controller
public class SumController {
//	@RequestMapping(value="input.do", method = RequestMethod.GET)
//	public ModelAndView input() { //사용자가 만든 콜백메소드, 요청이 input.do 라고 들어오면 스프링은 input()를 호출한다. 메소드명은 요청명과 동일하게 한다고 일단은 약속하였다.(마음대로써도상관없음)
//		ModelAndView mav = new ModelAndView();
//		//mav.setViewName("input"); //스프링에서는 ~.jsp 처럼 어떤 파일을 보낼건지 저장해야한다.
//		//input 이란 파일로 보낼 것이다.
//		mav.setViewName("/sum/input");
//		//<property name="prefix" value="/sum/"></property> xml 에서 이 문장을 사용하지 않고
//		//직접 폴더명까지 기술해서도 사용한다.
//		return mav;
//	}
	
	@RequestMapping(value="input.do", method = RequestMethod.GET)
	public String input() { 
		return "/sum/input";
	}
	//스프링의 특징
	//리턴 타입이 String이면 단순 문자열이 아니라 뷰이름(현재는 jsp의 이름)으로 사용된다.
	//만약 return "바보"; 를 한다면 스프링은 - 바보.jsp 를 찾는다
	
	//그런데 뷰의 이름이 아니라 실제 문자열로 리턴하고 싶을때는 @ResponseBody를 붙인다.
	//그러면 return 이 실린 값을 String 타입으로 보낸다.
	
	
	//----------------------------------------------------------
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result() {
//		ModelAndView mav = new ModelAndView();
//		//mav.setViewName("result");
//		mav.setViewName("/sum/result");
//		
//		return mav;
//	}
	
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam int x, @RequestParam int y) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.addObject("sum" ,x+y);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
//	result 에서 ${param.x} + ${param.y} = ${param.x + param.y} 바로 데이터를 받지 않고,
//	ModelAndView 에서 데이터를 받아 처리한 후 넘기는 코드
	
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result(
//					@RequestParam(required=false, defaultValue = "0") String x,
//					@RequestParam(required=false, defaultValue = "0") String y) { // 넘버포멧 엑셉션으로 거슬리는것때문에 넘어온 값을 String 처리 해버린것
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.addObject("sum" , Integer.parseInt(x)+Integer.parseInt(y));
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
	
	//위의 방식은 매개변수가 있을 때 마다 RequestParam 을 써야한다.
	//그것보다 매개변수가 많을 때 편하게 사용되는건 Map 을 이용한 방법이다.
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	//									데이터를 받는 쪽                          , 데이터를 내보내는쪽
//	public String result(@RequestParam Map<String,String> map, ModelMap modelMap) {
//		int x = Integer.parseInt(map.get("x"));
//		int y = Integer.parseInt(map.get("y"));
//		int sum = x + y;
//		//요청 Input.jsp 에서 넘어오는 데이터가 전부 String 타입이다. 
//		modelMap.put("x", map.get("x"));
//		modelMap.put("y", map.get("y"));
//		modelMap.put("sum", sum);
//		return "/sum/result";
//	}
	
	//Model 에 데이터를 실어 보내는 방법
	@RequestMapping(value="result.do", method=RequestMethod.GET)
	public String result(@ModelAttribute SumDTO sumDTO, Model model) {
		model.addAttribute("x", sumDTO.getX());
		model.addAttribute("y", sumDTO.getY());
		model.addAttribute("sum", sumDTO.getX() + sumDTO.getY());
		
		return "/sum/result";
	}
	
}
