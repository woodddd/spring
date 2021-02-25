<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>
*** ${sungJukDTO.getName() } 성적 ***<!-- requestScope가 생략되도됨 -->
</h3>
<hr>
총점 : ${sungJukDTO.tot }
평균 : ${sungJukDTO.avg }
</body>
</html>