package imageboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ImageboardPaging {
	private int currentPage;//현재페이지
	private int pageBlock;//[이전][1][2][3][다음]
	private int pageSize;//1페이지당5개씩
	private int totalA;//총글수
	private StringBuffer pagingHTML; //StringBuffer 타입은 수정이 가능하다.
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + (pageSize-1)) / pageSize;//총 페이지수
		
		int startPage = (currentPage -1) / pageBlock * pageBlock + 1;//페이지 시작번호
		
		int endPage = startPage + pageBlock - 1;//페이지 끝번호
		if(endPage > totalP) {
			endPage = totalP;
		}
		
		if(startPage > pageBlock) {
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging(" + (startPage-1) + ")'>이전</span>]");
		}
		
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage) {
				pagingHTML.append("[<span id='currentPaging' onclick='imageboardPaging("+i+")'>"+i+"</span>]");
			}else {
				pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+i+")'>"+i+"</span>]");
			}//if
		}//for
		
		if(endPage < totalP) {
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+(endPage+1)+")'>다음</span>]");
		}
			
			
	}
}
