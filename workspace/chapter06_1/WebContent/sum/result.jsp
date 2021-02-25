<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- SumController 에서 데이터 처리를 하지 않은 경우 -->
<%-- ${param.x} + ${param.y} = ${param.x + param.y}  --%>
<!-- 서블릿을 DispatcherServlet 을 사용하기 때문에 안에서 포워딩이 구현되어있음 -->
<!-- 현재 서블릿은 포워딩방식으로 동작하기 때문에 input.jsp 에서 사용한 변수를 그대로 쓸 수있음 -->


<!-- SumController 에서 데이터 처리를 한 경우 -->
${requestScope.x } + ${requestScope.y } = ${requestScope.sum }
</body>
</html>