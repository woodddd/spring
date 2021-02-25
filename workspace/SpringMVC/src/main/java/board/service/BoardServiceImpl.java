package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private HttpSession session; //request로부터 session을 얻어온다. 옛날방식 -> request.getSession()
	@Autowired
	private BoardPaging boardPaging;
	
	@Override
	public void boardWrite(Map<String, String> map) {
		map.put("id",(String)session.getAttribute("memId"));
		map.put("name",(String)session.getAttribute("memName"));
		map.put("email",(String)session.getAttribute("memEmail"));
		
		boardDAO.boardWrite(map);
	}

	@Override
	public List<BoardDTO> getBoardList(String pg) {
		//1페이지당 5개
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum",startNum);
		map.put("endNum",endNum);
		
		List<BoardDTO> list = boardDAO.getBoardList(map);
		
		return list;
	}
	
	@Override
	public List<BoardDTO> getBoardSearch(Map<String, String> map) {
		//1페이지당 5개
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		System.out.println("endNum=" + endNum);
		System.out.println("startNum=" + startNum);
		
		//map - pg,searchType,keyworrd,startNum,endNum
		map.put("startNum",startNum + "");
		map.put("endNum",endNum + "");
		
		return boardDAO.getBoardSearch(map);
	}

	@Override
	public BoardDTO getBoard(String seq) {
		return boardDAO.getBoard(seq);
	}

	@Override
	public void hitUpdate(String seq) {
		boardDAO.hitUpdate(seq);
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getTotalA();
		
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}
	
	@Override
	public BoardPaging boardPaging(Map<String, String> map) {
		int totalA = boardDAO.getSearchTotalA(map);
		
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}

	@Override
	public void boardModify(Map<String, String> map) {
		boardDAO.boardModify(map);
		
	}

	@Override
	public void boardDelete(String seq) {
		boardDAO.boardDelete(seq);
		
	}

	@Override
	public void boardReply(Map<String, String> map) {
		//원글
		BoardDTO pDTO = boardDAO.getBoard(map.get("pseq"));
		
		//map안에는 pseq, subject, content가 있다. 추가해야함.
		map.put("id", (String)session.getAttribute("memId"));
		map.put("name",(String)session.getAttribute("memName"));
		map.put("email",(String)session.getAttribute("memEmail"));
		map.put("ref", pDTO.getRef()+""); //ref = 원글의 ref
		map.put("lev", pDTO.getLev()+1+""); //lev = 원글lev + 1
		map.put("step", pDTO.getStep()+1+""); //step = 원글 step + 1
		
		boardDAO.boardReply(map);
	}

}
