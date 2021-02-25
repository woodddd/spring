package board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="boardWriteForm",method=RequestMethod.GET)
	public String boardWriteForm(Model model) {
		model.addAttribute("display","/board/boardWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="boardWrite",method=RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@RequestParam Map<String,String> map) {
		boardService.boardWrite(map);
	}
	
	@RequestMapping(value="boardList",method=RequestMethod.GET)
	public String boardList(@RequestParam(required = false, defaultValue="1") String pg,Model model) {
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardList.jsp");
		return "/index";
	}//값이 필수로 넘어와야 하는 것이 아니며,값이 넘어오지 않을 땐, 기본값을 1로 하겠다.
	//required = false, defaultValue="1"
		
	@RequestMapping(value="getBoardList",method=RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required = false, defaultValue="1") String pg,
										HttpSession session,
										HttpServletResponse response) {
		
		List<BoardDTO> list = boardService.getBoardList(pg);
		
		String memId = (String) session.getAttribute("memId");
		
		//조회수 - 새로고침 방지
		if(session.getAttribute("memId") != null) {
			Cookie cookie = new Cookie("memHit","0");
			cookie.setMaxAge(30*60);//30분 시간 설정(초단위라 * 60해준것임)
			response.addCookie(cookie);//클라이언트에게 보내기
		}
		
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("memId",memId);
		mav.addObject("boardPaging",boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getBoardSearch", method=RequestMethod.POST)
	public ModelAndView getBoardSearch(@RequestParam Map<String,String> map) {
		List<BoardDTO> list = boardService.getBoardSearch(map); //pg, searchType, keyword
		System.out.println("getBoardSearch pg = " + map.get("pg"));
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardView",method=RequestMethod.GET)
	public String boardView(@RequestParam String seq,
							@RequestParam(required = false, defaultValue="1") String pg,
							Model model){

		model.addAttribute("seq",seq);
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardView.jsp");
		
		return "/index";
	}
	
	@RequestMapping(value="getBoard",method=RequestMethod.POST)
	public ModelAndView getBoard(@RequestParam String seq,
								@CookieValue(value="memHit", required=false) Cookie cookie,
								HttpServletResponse response,
								HttpSession session) {
		//memHit라는 쿠키를 가져와라. 없다면 안가져와도된다.(required=false)
		//쿠키의 값은 있을수도 있고 없을수도 있다.
		
		//조회수 - 새로고침 방지
		if(cookie != null) {
			boardService.hitUpdate(seq);
			cookie.setMaxAge(0); //쿠키 삭제
			response.addCookie(cookie); // 쿠키 삭제된걸 클라이언트에게 보내기.
			//쿠키가 살아있는 경우 - List로 나간경우,
			//쿠키가 죽은경우 - 현재 페이지에서 hit 를 증가한 경우, 만약 새로고침 한다면
			//쿠키가 죽어있으므로(null) hit 수가 증가하지않음.
		}
		BoardDTO boardDTO = boardService.getBoard(seq);
		
		String memId = (String) session.getAttribute("memId");
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO",boardDTO);
		mav.addObject("memId",memId);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardModifyForm",method=RequestMethod.POST)
	public String boardModifyForm(@RequestParam String seq,
								  @RequestParam String pg,
								  Model model) {
		
		model.addAttribute("seq",seq);
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardModifyForm.jsp");
		
		return "/index";
	}
	
	@RequestMapping(value="boardModify",method=RequestMethod.POST)
	@ResponseBody
	public void boardModify(@RequestParam Map<String, String> map) {
		boardService.boardModify(map);
	}
	
	@RequestMapping(value="boardDeleteForm",method=RequestMethod.POST)
	public String boardDeleteForm(@RequestParam String seq, Model model) {
		//boardService.boardDelete(seq);
		
		model.addAttribute("seq", seq);
		model.addAttribute("display","/board/boardDelete.jsp");
		return "/index";
	}
	
	@RequestMapping(value="boardDelete",method=RequestMethod.POST)
	@ResponseBody
	public void boardDelete(@RequestParam String seq, Model model) {
		boardService.boardDelete(seq);
	}
	
	@RequestMapping(value="boardReplyForm",method=RequestMethod.POST)
	public String boardReplyForm(@RequestParam String seq,
			  					 @RequestParam String pg,
			  					 Model model) {
		model.addAttribute("pseq",seq); //원글번호
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardReplyForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="boardReply",method=RequestMethod.POST)
	@ResponseBody
	public void boardReply(@RequestParam Map<String,String> map) {
		boardService.boardReply(map);
	}
	
	
}
