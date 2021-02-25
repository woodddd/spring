<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- <style type="text/css">
form[name=imageboardWriteForm] {
font-size: 15px;
}
</style> -->

	<!-- submit으로 하는 경우 -->
	<!-- <form id="imageboardWriteForm" method="post" encType="multipart/form-data" action="imageboardWrite"> -->
	
	<!-- ajax통신하는 경우 -->
	<form id="imageboardWriteForm">
	<h3>상품 등록</h3>
	<hr>
		<table border="1" cellpadding="3" cellspacing="1">
			<tr>
				<td align="center">상품코드<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
				<td>
					<input type="text" id="imageId" name="imageId" value="img_" size="30" placeholder="상품코드 입력">
					<div id="imageIdDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td align="center">상품명<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
				<td>
					<input type="text" id="imageName" name="imageName" size="30" placeholder="상품명 입력">
					<div id="imageNameDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td align="center">단가<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
				<td>
					<input type="text" id="imagePrice" name="imagePrice" size="30" placeholder="단가 입력">
					<div id="imagePriceDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td align="center">개수<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
				<td>
					<input type="text" id="imageQty" name="imageQty" size="30" placeholder="개수 입력">
					<div id="imageQtyDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td align="center">내용<span style="color:red; font-weight:bold;">&nbsp;*</span></td>
				<td>
					<textarea id="imageContent" name="imageContent" cols="50" rows="10" placeholder="내용입력"></textarea>
					<div id="imageContentDiv"></div>
				</td>
			</tr>
			
			<!-- <tr>
				<td colspan="2">
					<input type="file" id="image1" name="img" size="50">
				</td>
			<tr>
			
			<tr>
				<td colspan="2">
					<input type="file" id="image2" name="img" size="50">
				</td>
			<tr> -->
			
			<tr>
				<td colspan="2">
					<input type="file" id="image3" name="img[]" size="50" multiple> <!-- multiple - 파일을 드래그해서 쭉 올리겠다. -->
				</td>
			<tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="이미지 등록" id="imageboardWriteBtn">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/imageboard.js"></script>
