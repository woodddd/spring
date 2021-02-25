package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.SungJukDTO;

@Controller
public class SungJukController {
	@RequestMapping(value="/sungJuk/input.do", method=RequestMethod.GET)
	public String input() {
		return "/sungJuk/input"; //단순 문자열이 아니라 JSP의 파일명으로 인식
	}
	
	@RequestMapping(value="/sungJuk/result.do", method=RequestMethod.POST)
	public String result(@ModelAttribute SungJukDTO sungJukDTO, ModelMap modelMap) {
		//sungJukDTO 에는 input.jsp 에 있는 name 변수와 같은 변수명인, 이름, 국어,영어, 수학 성적만 들어오게 됨. tot와 avg 는 없음
		int tot = sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath();
		double avg = tot/3.0;
		
		sungJukDTO.setTot(tot);
		sungJukDTO.setAvg(avg);
		
		modelMap.addAttribute("sungJukDTO", sungJukDTO);
		return "/sungJuk/result"; 
	}
}
