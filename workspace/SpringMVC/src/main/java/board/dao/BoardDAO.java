package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {

	public void boardWrite(Map<String, String> map);

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public BoardDTO getBoard(String seq);

	public void hitUpdate(String seq);

	public int getTotalA();

	public int getSearchTotalA(Map<String, String> map);
	
	public void boardModify(Map<String, String> map);

	public void boardDelete(String seq);

	public void boardReply(Map<String, String> map);

	public List<BoardDTO> getBoardSearch(Map<String, String> map);

}
