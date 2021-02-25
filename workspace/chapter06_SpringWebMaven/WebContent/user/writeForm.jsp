<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	color: red;
	font-size: 7pt;
	font-weight: bold;
}
</style>
</head>
<body>
<form id="writeForm" name="writeForm"> <!-- ajax로 써주었기 때문에 내용이 필요 없어짐. -->
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
      <input type="text" name="id" id="id">
      <input type="button" id="checkIdBtn" value="중복체크">
      <input type="hidden" name="check" value="">
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
      <input type="button" value="회원가입" id="writeBtn">
      <input type="reset" value="다시작성">
   </td>
</tr>
</table>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery 를 다운받지 않고 사용하고 싶을 때, -->
<!-- 최소한의 사양으로 jquery 파일을 받겠다. -->
<!-- jquery 는 $('???') 를 사용하는데 -> 함수가 ?? 객체를 리턴해준다. -->
<!-- $('#writeBtn').click(function(){
	if($('#name').val()=='')
		$('nameDiv').text('이름을 입력하세요');
}); -> id가 writeBtn인 객체를 클릭했을 때 function() 일을 해라.. --> 
<script type="text/javascript" src="../js/user.js"> 
/* / 가 들어오기 때문에 dispatcher 서블릿으로 간다. 그래서 servlet-context.xml 에서 코드를 수정해야한다. */
/* function checkWrite(){
	if(document.getElementById("name").value == "")
		document.getElementById("nameDiv").innerText = "이름을 입력하세요";
} */

</script>
</body>
</html>