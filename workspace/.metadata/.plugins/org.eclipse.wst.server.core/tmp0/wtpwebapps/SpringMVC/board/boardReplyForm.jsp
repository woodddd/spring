<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form>

<input type="hidden" id="pseq" value="${pseq }">
<input type="hidden" id="pg" value="${pg }">

<h3>답글쓰기</h3>
<hr>
	<table border="1" cellpadding="3" cellspacing="1">
		<tr>
			<td align="center">제목</td>
			<td>
			<input type="text" id="subject" name="subject" placeholder="제목입력">
			<div id="subjectDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td align="center">내용</td>
			<td>
			<textarea id="content" name="content" cols="50" rows="15" placeholder="내용입력"></textarea>
			<div id="contentDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="답글쓰기" id="boardReplyBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type = "text/javascript">
$('#boardReplyBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	
	if($('#subject').val()==''){
		$('#subjectDiv').text("제목을 입력해주세요");
		$('#subjectDiv').css('color','red');
		$('#subjectDiv').css('font-size','8pt');
		$('#subjectDiv').css('font-weight','bold');
		
	}else if($('#content').val()==''){
		$('#contentDiv').text("내용을 입력해주세요");
		$('#contentDiv').css('color','red');
		$('#contentDiv').css('font-size','8pt');
		$('#contentDiv').css('font-weight','bold');
		
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/board/boardReply',
			data: { 'pseq' : $('#pseq').val(),//원글번호
					'pg' : $('#pg').val(),//원글이 있는 페이지번호
					'subject' : $('#subject').val(),
				    'content' : $('#content').val()} ,
			success: function(){
				alert('답글쓰기 완료');
				location.href='/spring/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
</script>
