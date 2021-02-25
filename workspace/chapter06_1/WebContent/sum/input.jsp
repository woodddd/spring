<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="inputForm" method="get" action="result.do">
	<table border="1">
		<tr>
			<td >X</td><td><input type="text" name="x" style="width: 60px"></td>
		</tr>
		
		<tr>
			<td>Y</td><td><input type="text" name="y" style="width: 60px"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="계산" ><input type="reset" value="취소"></td>
		</tr>
	</table>
</form>
</body>
</html>