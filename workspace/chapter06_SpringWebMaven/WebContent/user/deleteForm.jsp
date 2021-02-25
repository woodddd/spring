<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 우석 버전
삭제할 아이디 입력 : <input type="text" id="deleteId">
<input type="button" id="deleteBtn" value="삭제">

<input type="hidden" id="id">

<br><br>
<div id="resultDiv"></div> 
-->
삭제할 아이디 입력 : <input type="text" id="searchId">
<input type="button" id="searchBtn" value="검색">
<br><br>
<div id="resultDiv"></div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
/* 내가한것.
$(document).ready(function(){
	   
	   $('#deleteBtn').click(function(event){
		   $('#resultDiv').empty();
		   
		   if($('#deleteId').val()==''){
			   $('#resultDiv').text('먼저 아이디를 입력하세요');
			   $('#resultDiv').css('color', 'red');
			   $('#resultDiv').css('font-size', '10pt');
			   $('#resultDiv').css('font-weight', 'bold');
		   }else{
			   if($('#id').val()==''){
				   $.ajax({
					   type: 'post',
					   url: '/chapter06_SpringWebMaven/user/getUser',
					   data: {'id' : $('#deleteId').val()},
				   	   dataType: 'json',
				   	   success: function(data){
				   		   //alert(JSON.stringify(data));
				   		   if($.isEmptyObject(data)){ //data의 값이 비어있으면 true 만약 부정의 조건은 앞에 ! 를붙이면 됨.
				   			   $('#resultDiv').text('삭제할 아이디가 없습니다.');
				   			   $('#resultDiv').css('color', 'red');
				 		       $('#resultDiv').css('font-size', '10pt');
				 		       $('#resultDiv').css('font-weight', 'bold');
				   		   }else{
				   			   $('#id').val(data.id);
				   			   $('#deleteBtn').trigger('click');
				   		   }
				   	   }
				   
				   });
			   }else if($('#id').val()!=''){
				   if(confirm('정말로 삭제하시겠습니까?')){
					   $.ajax({
						   type: 'post',
						   url: 'delete',
						   data: {'id' : $('#deleteId').val()},
						   success: function(){
							   alert("회원정보 삭제 완료");
							   location.href='/chapter06_SpringWebMaven/index.jsp'
						   },
						   error: function(){
							   alert("회원정보 삭제 실패");
						   }
						   
					   }); 
				   }else{
					   alert('삭제를 취소합니다.');
					   $('#id').val('');
				   }
			   }
			   
		   }
	   });
	   
});
*/

/* 강사님 버전 */
$(document).ready(function(){
	$('#searchBtn').click(function(){
		$('#resultDiv').empty();
		
		if($('#searchId').val()==''){
			   $('#resultDiv').text('먼저 아이디를 입력하세요');
			   $('#resultDiv').css('color', 'red');
			   $('#resultDiv').css('font-size', '10pt');
			   $('#resultDiv').css('font-weight', 'bold');
		}else{
			$.ajax({
				type: 'POST',
				url: '/chapter06_SpringWebMaven/user/getUser',
				data: {'id' : $('#searchId').val()},
				dataType: 'json',
				success: function(data){
					//alert(JSON.stringify(data));
					if(JSON.stringify(data) == JSON.stringify({})){
						$('#resultDiv').text('삭제할 아이디가 없습니다.');
						$('#resultDiv').css('color', 'red');
						$('#resultDiv').css('font-size', '10pt');
						$('#resultDiv').css('font-weight', 'bold');
					}else{
						if(confirm("정말로 삭제하시겠습니까?")){
							$.ajax({
								type: 'POST',
								url: '/chapter06_SpringWebMaven/user/delete',
								data: {'id' : $('#searchId').val()},
								success: function(){
									alert("회원정보 삭제 완료");
									location.href='/chapter06_SpringWebMaven/index.jsp';
								}
							});
						}
					}
				},
				error: function(err){
					console.log(err);
				}
			});	
		}
	});
});//stringify - JSON형태 그대로 문자열로 반환한다.
//JSON.stringify(data) == JSON.stringify({})   JSON으로넘어온 객체를 문자열로바꿔 값이 같은지 비교
//{} - JSON객체의 빈값이 넘어올 때.
</script>
</body>
</html>