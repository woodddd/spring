<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
수정할 아이디 입력 : <input type="text" id="searchId">
<input type="button" id="searchBtn" value="검색">
<br><br>
<div id="resultDiv"></div>

<div id="modifyFormDiv">
<form id="modifyForm">
<table border="1" cellpadding="3" cellspacing="0">
<tr>
   <td width="100">이름</td>
   <td>
      <input type="text" name="name" id="name">
      <div id="nameDiv"></div>
   </td>
</tr>

<tr>
   <td width="100">아이디</td>
   <td>
      <input type="text" name="id" id="id" readonly>
      <div id="idDiv"></div>
   </td>
</tr>

<tr>
   <td width="100">비밀번호</td>
   <td>
      <input type="password" name="pwd" id="pwd">
      <div id="pwdDiv"></div>
   </td>
</tr>

<tr>
   <td colspan="2" align="center">
      <input type="button" value="회원정보수정" id="modifyBtn">
      <input type="reset" value="다시작성" id="resetBtn">
   </td>
</tr>
</table>
</form>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $('#modifyFormDiv').hide(); //hide() - hidden. 숨긴다.
   
   $('#resetBtn').click(function(){
	   //강제로 이벤트 발생  -  여기서는 reset을 누르면 값을 모두 지우는 것이 아닌, 수정할 아이디 입력란에 입력된 id 에 대한 정보를
	   //다시 불러와야 하는 것이다. 그래서 아래의 기능을 다시 구현하게 되면 코드의 중복이 일어난다. 그래서 이벤트를 강제로 발생시켜 코드의 중복을 최소화한다.
	   $('#searchBtn').trigger('click');//trigger 안에 click을 강제로 매개변수로 넣어주어 $('#searchBtn').click(function(event){ 문장의 event에 'click' 이 동작하게 한다.
	   
   });
   
   
   $('#searchBtn').click(function(event){
	   $('#resultDiv').empty();
	   
	   if($('#searchId').val()==''){
		   $('#resultDiv').text('먼저 아이디를 입력하세요');
		   $('#resultDiv').css('color', 'red');
		   $('#resultDiv').css('font-size', '10pt');
		   $('#resultDiv').css('font-weight', 'bold');
	   }else{
		   $.ajax({
			   type: 'post',
			   url: '/chapter06_SpringWebMaven/user/getUser',
			   data: {'id' : $('#searchId').val()},
		   	   dataType: 'json',
		   	   success: function(data){
		   		   //alert(JSON.stringify(data));-넘어온데이터 찍어보기
		   		   
		   		   if($.isEmptyObject(data)){ //data의 값이 비어있으면 true 만약 부정의 조건은 앞에 ! 를붙이면 됨.
		   			   $('#resultDiv').text('수정할 아이디가 없습니다.');
		   			   $('#resultDiv').css('color', 'red');
		 		       $('#resultDiv').css('font-size', '10pt');
		 		       $('#resultDiv').css('font-weight', 'bold');
		   		   }else{
		   			   $('#modifyFormDiv').show();//hide로 숨긴걸 보여줘라.
		   			   
		   			   $('#name').val(data.name);
		   			   $('#id').val(data.id);
		   		   }
		   	   }
		   
		   });
	   }
   });
   
   $('#modifyBtn').click(function(){
	   $('#nameDiv').empty();
	   $('#pwdDiv').empty();
	   
	   if($('#name').val()=='')
	      $('#nameDiv').text('이름을 입력하세요').css('color','red').css('font-size','8pt').css('font-weight', 'bold');
	   else if($('#pwd').val()=='')
	      $('#pwdDiv').text('비밀번호를 입력하세요').css('color','red').css('font-size','8pt').css('font-weight', 'bold');
	   else
		   $.ajax({
			   type: 'post',
			   url: 'modify',
			   data: {'name' : $('#name').val(),
				   	'id' : $('#id').val(),
				   	'pwd' : $('#pwd').val()}
		   		//$('#modifyForm').serialize()를 사용해도됨. 그러면 기존에 사용하던 jsp 코드로 보내줌.
		   		//단, name 변수가 무조건 존재해야 한다.
		   		,
			   success: function(){
				   alert("회원정보 수정 완료");
				   location.href='/chapter06_SpringWebMaven/index.jsp'
			   },
			   error: function(){
				   alert("회원정보 수정 실패");
			   }
			});
   });
   
});
</script>
</body>
</html>