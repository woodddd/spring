package member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="loginForm",method=RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("display","/member/loginForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody //순수 문자열로 반환한다.
	public String login(@RequestParam Map<String,String> map, HttpSession session) {
		//컨트롤러는 스프링으로 부터 session객체를받아올 수 있다. 
		return memberService.login(map, session);
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); //모든세션 무효화
		return "/index";
	}
	
	@RequestMapping(value="writeForm",method=RequestMethod.GET)
	public String writeForm(Model model) {
		//Model은 View에게 데이터를 전달하기 위해 사용한다.
		//반면 ModelAndView는 데이터와 ViewName을 보낼 수 있다.
		model.addAttribute("display","/member/writeForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="write",method=RequestMethod.POST)
	public String write(@ModelAttribute MemberDTO memberDTO, Model model) {
		int su = memberService.write(memberDTO);
		
		model.addAttribute("su", su);
		model.addAttribute("display", "/member/write.jsp");
		return "/index";
	}

	@RequestMapping(value="checkId",method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		return memberService.checkId(id);//문자열 그대로 데이터 리턴
	}
	
	@RequestMapping(value="checkPost",method=RequestMethod.GET)
	public String checkPost() {
		return "/member/checkPost";
	}
	
	@RequestMapping(value="checkPostSearch",method=RequestMethod.POST)
	public ModelAndView checkPostSearch(@RequestParam Map<String,String> map) {
		List<ZipcodeDTO> list = memberService.checkPostSearch(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");//BeanNameViewResolver 로 가서 jsonView bean 의 이름을 찾아서 데이터를 처리할 것임.
		return mav;
	}
	
	@RequestMapping(value="modifyForm",method=RequestMethod.GET)
	public String modifyForm(HttpSession session,Model model) {
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMember(id);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/member/modifyForm.jsp");
		
		return "/index"; 
	}
	
	@RequestMapping(value="modify",method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute MemberDTO memberDTO) {
		memberService.modify(memberDTO);
	}
}
