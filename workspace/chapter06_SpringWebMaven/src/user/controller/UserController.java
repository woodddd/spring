package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import user.bean.UserDTO;
import user.service.UserService;

@Controller
//@Component
@RequestMapping("/user") //RequestMapping이 있으면 @Controller가 빠져도된다.
public class UserController {
	@Autowired
	private UserService userService;

	//@RequestMapping(value="/user/writeForm",method=RequestMethod.GET)
	//클래스에 @RequestMapping("/user") 를 걸지 않았을 때는 /user 까지 붙여줬음.
	@RequestMapping(value="/writeForm",method=RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	@ResponseBody //viewResolver로 가지 말고 요청을 한 곳으로 다시 돌아가라. 그러므로 return 주소가 사라지니까 타입을 void 로 변환함.
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
		
	}
//	wirte는 스프링이 호출하는 콜백메소드이다.
//	->그러므로 @ModelAttribute를 통해 UserDTO를 생성해서 값을 받는다
//	그러므로 UserDTO객체를 따로 생성할 필요가없다
	
	@RequestMapping(value="/checkId",
					produces = "application/String;charset=UTF-8",
					method=RequestMethod.POST)
	public @ResponseBody String checkId(String id) {//반환되는 자료형이 뷰네임이 아닌 문자열 그자체.
		String result = userService.checkId(id);
		System.out.println(result);
		return result;
	}//result의 값을 javascript가 받는다. 그래서 자바스크립트에서 넘어온 값을 자바에서 받으려면 한글로 제대로 받을 수 있도록 변환해주어야한다.
	
	/*
	jquery를 이용하지 않은 코드
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView userList() {
		
		List<UserDTO> list = userService.getUserList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list",list);
		mav.setViewName("/user/list");
		
		return mav;
	}
	*/
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String userList() {
		return "/user/list";
	}
	
	/* JSON 을 사용한 오리지날 버전!(가장기본적인방식)
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getUserList() {
		List<UserDTO> list = userService.getUserList();
		System.out.println(list);
		
		JSONObject json = new JSONObject();
		if(list != null) {
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < list.size(); i ++) {
				UserDTO userDTO = list.get(i);
				
				JSONObject temp = new JSONObject();
				temp.put("name", userDTO.getName());
				temp.put("id", userDTO.getId());
				temp.put("pwd", userDTO.getPwd());
			
				array.add(i, temp);
			}//for
			json.put("list", array);
		}
		System.out.println(json);
		return json;
	}//json 은 map처럼 동작한다.
	*/
	
	/*
	//위의 오리지날 방법을 간소화한것.
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	@ResponseBody
	public Map getUserList() {
		List<UserDTO> list = userService.getUserList();
		
		JSONArray array = JSONArray.fromObject(list);
		//리스트 안에 담겨있는 UserDTO 를 하나의JSON객체로 만들고
		//JSON배열에 담아준다.
		Map map = new HashMap();
		map.put("list", list);
		return map; //Map 을 출력하게 되어도, @ResponseBody 를 사용하였으므로,
		//MessageConverter 의 기본 방식인 JSON 타입으로 반환이 된다.
	}
	*/
	
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getUserList() {
		List<UserDTO> list = userService.getUserList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}//기존에는 ModelAndView 를리턴하면 Resolver를 찾아갔지만,
	//Resolver의 우선순위를 BeanNameViewResolver 를 0순위로 주었기 때문에,
	//BeanNameViewResolver를 이용하여 jsonView 이름의 객체를 찾는다.
	//만약 mav.setViewName("jsonView2")를 하게되면 jsonView2객체를 찾게 되는데
	//찾는 객체가 없다면 null을 반환학, 그다음 우선순위를 가진 Resolver를 찾아가게 되어
	//ViewResolver로 가는 것이다. 하지만 현재ResponseBody 어노테이션을 사용하였으므로,
	//다시 요청지인 $.ajax로 돌아간다.
	
	@RequestMapping(value="/modifyForm",method=RequestMethod.GET)
	public String modifyForm() {
		return "/user/modifyForm";
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	@ResponseBody //절대 resulver를 타고 jsp로 가면 안된다.다시 ajax로 돌아갈 때엔, 꼭 @ResponseBody를 사용해야 한다.
	public JSONObject getUser(@RequestParam String id) {
		UserDTO userDTO = userService.getUser(id);
		
		JSONObject json = new JSONObject();
		if(userDTO != null) {
			json.put("name", userDTO.getName());
			json.put("id", userDTO.getId());
			json.put("pwd", userDTO.getPwd());
		}
		//만약 결과가 null 이면 json 은 {} 을 가지고 간다. 제이슨객체는 생성했는데 담기는 DTO가 널이라 값이 없는것.
		return json;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute UserDTO userDTO) {
		userService.modify(userDTO);
	}
	
	@RequestMapping(value="/deleteForm",method=RequestMethod.GET)
	public String deleteForm() {
		return "/user/deleteForm";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.delete(id);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	//public JSONObject search(@RequestParam String searchOption,@RequestParam String searchText){
	public JSONObject search(@RequestBody Map<String, String> map) {
		//List<UserDTO> list = userService.search(searchOption,searchText);
		System.out.println("searchOption="+ map.get("searchOption"));
		
		//JSON형태로 데이터를 받아올 때에는 @RequestBody를 꼭 사용해주어야 한다는게 가장 중요!!
		
		//List<UserDTO> list = userService.search(searchOption, searchText);
		List<UserDTO> list = userService.search(map);
		
		JSONObject json = new JSONObject();
		if(list != null) {
			JSONArray array = new JSONArray();
			
			for(int i=0; i<list.size(); i++) {
				UserDTO userDTO = list.get(i);
				
				JSONObject temp = new JSONObject();
				temp.put("name", userDTO.getName());
				temp.put("id", userDTO.getId());
				temp.put("pwd", userDTO.getPwd());
				
				array.add(i, temp);
			}//for
			
			json.put("list", array);
		}
		
		System.out.println(json);
		return json;
	}//@RequestBody json형태의 문자열로 데이터가 넘어온다. 그걸 map이랑 매핑해라.
	//단 위와같이 쓰려면 $.ajax의 요청에서 contentType: 'application/json;charset=UTF-8',
	//을 꼭 지정해 주어야 JSON형태가 문자열처리되어 넘어감.
	//@RequestBody는 json형태로 넘어온 문자열을 처리할 때 필수적으로 사용한다.
	//JSON형태가 아닌 문자열이 올땐 RequestParam 으로 데이터를 받아준다.
}








