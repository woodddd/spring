<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form id="boardWriteForm">
<h3>글쓰기</h3>
<hr>
	<table border="1" cellpadding="3" cellspacing="0">
		<tr>
			<td align="center" width="60px">제목<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
			<td>
			<input size="70" type="text" id="subject" name="subject" placeholder="제목입력">
			<div id="subjectDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td align="center">내용<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
			<td>
			<textarea id="content" name="content" cols="70" rows="15" placeholder="내용입력"></textarea>
			<div id="contentDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글쓰기" id="boardWriteBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type = "text/javascript" src="../js/board.js"></script>
