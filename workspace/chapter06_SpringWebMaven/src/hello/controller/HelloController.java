package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class HelloController {
	
	/*
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public @ResponseBody String hello() {
		return "안녕하세요 JSON!!";
	}//@ResponseBody 를 통해 반환 자료형이 String 이므로, 문자열 그대로 처리한다.
	//그래서 hello 요청을 하게 되면, return 값으로 화면에 HelloJSON!!이 찍힌다.
	//@ResponseBody 어노테이션을 만나면 dispatcherServlet 에서 StringHttpMessageConverter 를 찾아간다.
	*/
	
	/*
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public @ResponseBody JSONObject hello() {
		JSONObject json = new JSONObject();
		json.put("name", "hong");
		json.put("age", 25);
		return json;
	}// jackson Maven 을 두개 추가하여 json 을 json 형식 그대로 웹에서 보이게 해준다.
	*/
	
	/*
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> hello() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "홍길동");
		map.put("age", 25);
		return map;
	}// map을 리턴하더라도 @ResponseBody 에의해 MessageConverter로 처리되고, JSON 형태로 바뀐다.
	//MessageConverter 는 기본값을 JSON 으로 한다.
	*/
	
	/*
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public @ResponseBody JSONArray hello() {
		JSONArray array = new JSONArray();
		
		JSONObject temp = new JSONObject();
		temp.put("name", "홍길동");
		temp.put("age", 25);
		array.add(0, temp);
		
		temp = new JSONObject();
		temp.put("name", "코난");
		temp.put("age", 17);
		array.add(1, temp);
		
		return array;
	}
	*/
	
	
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public @ResponseBody JSONObject hello() {
		JSONArray array = new JSONArray();
		
		JSONObject temp = new JSONObject();
		temp.put("name", "홍길동");
		temp.put("age", 25);
		array.add(0, temp);
		
		temp = new JSONObject();
		temp.put("name", "코난");
		temp.put("age", 17);
		array.add(1, temp);
		
		JSONObject json = new JSONObject();
		json.put("json", array);
		
		return json;
		
	}
	
}
