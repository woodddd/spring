<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form id="boardModifyForm">

<input type="hidden" name="seq" id="seq" value="${seq }">
<input type="hidden" name="pg" id="pg" value="${pg }">
<h3>글수정</h3>
<hr>
	<table border="1" cellpadding="3" cellspacing="1">
		<tr>
			<td align="center">제목</td>
			<td>
			<input type="text" id="subject" name="subject">
			<div id="subjectDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td align="center">내용</td>
			<td>
			<textarea id="content" name="content" cols="50" rows="15" ></textarea>
			<div id="contentDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글수정" id="boardModifyBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type = "text/javascript">
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoard',
		data: 'seq='+$('#seq').val(),
		dataType: 'json',
		success: function(data){
			alert(JSON.stringify(data));
			
			$('#subject').val(data.boardDTO.subject);
			$('#content').val(data.boardDTO.content);
		},
		error: function(err){
			console.log(err);
		}
	});
});

$('#boardModifyBtn').click(function(){
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
			url: '/spring/board/boardModify',
			data: $('#boardModifyForm').serialize() ,
			success: function(){
				alert('글수정 완료');
				location.href='/spring/board/boardList?pg='+$('#pg').val();
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
</script>
