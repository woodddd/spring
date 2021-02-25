<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/spring/css/index.css">
</head>
<body>
<div class="loginModal">
      	<div class="loginModalContent">
      		<div class="loginModal-header">
				<div class="loginModal-headerDiv">
					<h3 class="loginModal-title" id="myModalLabel">로그인</h3>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
	  		</div>
	  		
		<form name="loginForm" method="post" action="login.do">
		<table cellpadding="3" cellspacing="0" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td align="center">아이디</td>
			<td>
				<input type="text" name="id" id="loginId" size="25">
				<div id="loginIdDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td align="center">비밀번호</td>
			<td>
				<input type="password" name="pwd" id="loginPwd" size="30">
				<div id="loginPwdDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="button" id="loginBtn" value="로그인"> 
			<input type="button" id="writeFormBtn" value="회원가입" onclick="location.href='/spring/member/writeForm'">
		</tr>
		</table>
		</form>
   </div> 
</div>



<div id="header">
	<!-- . 점 하나는 현재 폴더에서 찾으라는 말이다. -->
	<h1><img src="/spring/image/mainImageBtn.PNG" width="50" height="50" 
	onclick="location.href='/spring/index.jsp'" style="cursor: pointer;">MVC 기반의 미니 프로젝트</h1>
</div>
<div id="container">
	<div id="nav">
		<c:if test="${sessionScope.memId == null }">
			<a href="/spring/member/writeForm"><button type="button" class="menuBtn">회원가입</button></a><br>
			
			<a><button type="button" class="loginForm">로그인</button></a><br>
			
		</c:if>

		<c:if test="${sessionScope.memId != null }">
			<h3>${memName }님 로그인</h3>
			<a href="/spring/member/logout"><button type="button" class="menuBtn">로그아웃</button></a><br>
			<a href="/spring/member/modifyForm"><button type="button" class="menuBtn">회원정보수정</button></a><br>
			<a href="/spring/board/boardWriteForm"><button type="button" class="menuBtn">글쓰기</button></a><br>
			<a href="/spring/imageboard/imageboardWriteForm"><button type="button" class="menuBtn">상품 등록</button></a><br>
		</c:if>
			<a href="/spring/board/boardList"><button type="button" class="menuBtn">목록</button></a><br>
			<a href="/spring/imageboard/imageboardList"><button type="button" class="menuBtn">상품 목록</button></a><br>
	</div>

	<div id="section">
		<c:if test="${not empty display }">
		<jsp:include page="${display }" />
		</c:if>
		<c:if test="${empty display }">
			<h1 align="center">우석이의 개인 홈페이지 입니다.<br>
				부족하지만 잘 봐주세요!
			</h1>
			<img alt="인사해" src="/spring/image/mainTube.png" class="hi" width="35%" height="50%">
			
		</c:if>
	</div>	
</div>
<%-- ${display } == '' -> display 변수는 존재하는데 값이 없다.
display 변수자체가 없다. null 그래서 not empty 릁 통해 현재 dispay 가 empty 가 아닐때로 조건을봐야한다. --%>

<div id="footer">
	<p>홈페이지 담당자 : 김우석
	<p>연락처 : 010-3696-8504
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/spring/js/index.js"></script>
</body>
</html>