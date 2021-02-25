<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <div id="display"></div> -->
<table id="table" border="1" cellpadding="3" cellspacing="0">
	<tr>
		<th width="100">이름</th>
		<th width="100">아이디</th>
		<th width="100">비밀번호</th>
	</tr>
</table>
<br><br>
<div class="search">
   <select name="searchOption" id="searchOption">
      <option value="">선택
      <option value="name">이름
      <option value="id">아이디
   </select> 
   <input type="text" id="searchText" name="searchText">
   <button id="searchBtn">검색</button>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- 뒤에 /script 태그를 닫지 않고, 문장끝에 / 를 붙이면 src를 제대로 읽어오질 못한다 -->
<script type="text/javascript">

//이 jsp파일을 읽어들일 때, 자바스크립트의 onload 이벤트와 동일
//$.ajax -> jquery의 ajax
$(document).ready(function(){
	$('#searchBtn').click(function(){
		if($('#searchOption').val()==''){
			alert('검색 옵션을 선택하세요');
			return false;
		
		}else{
			$('#table tr:gt(0)').remove();
			
			$.ajax({
				type: 'post',
				url: '/chapter06_SpringWebMaven/user/search',
				
				//data: 'searchOption='+$('#searchOption').val()+"&searchText="+$('#searchText').val(),
				data: JSON.stringify({'searchOption': $('#searchOption').val(),
									  'searchText': $('#searchText').val()}),
				contentType: 'application/json;charset=UTF-8',
				dataType: 'json',
				success: function(data){
					//alert(JSON.stringify(data));
					
					$.each(data.list, function(index, items){
						$('<tr/>').append($('<td/>',{
							align: 'center',
							text: items.name
						})).append($('<td/>',{
							align: 'center',
							text: items.id
						})).append($('<td/>',{
							align: 'center',
							text: items.pwd
						})).appendTo('#table');
					});
				}
			});
		}
	});
		
	//gt( > ) 아이디가 table 인 후손의 tr 중 > 0 보다 큰것을 지워라.(0행보다 큰 줄을 모두 지워라)
	
	//data: 'searchOption='+$('#searchOption').val()+"&searchText="+$('#searchText').val(),
	//기존 형식으로 데이터를넘김
	//아래는 JSON형태 그대로 문자열로 보냄
	//data: JSON.stringify({'searchOption': $('searchOption').val(),
									  //'searchText': $('#searchText').val()}),
	//JSON형태 그대로 문자열로변환해서 보냄.(매우중요)
	//{'searchOption': $('#searchOption').val(),'searchText': $('#searchText').val()}
	
	//contentType: 'application/json;charset=UTF-8',
	//data로 json객체를 보낼 때,는 contentType 을 지정하여 json 이라는 데이터를 보낸다는걸 알려주어야 한다.
	
	//현재 stringify 를 통해 JSON의 형식 그대로 문자열로 바꾸어서 데이터를 요청하고,
	//Controller에서는 @RequestBody 를 이용하여 Map 에 데이터를 저장한다.
	
	
	$.ajax({
		type: 'post',
		url: '/chapter06_SpringWebMaven/user/getUserList',
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			//방법1
			/* var table = '<table border=1>';
			table += '<tr>';
			table += '<th width="100">이름</th>';
			table += '<th width="100">아이디</th>';
			table += '<th width="100">비밀번호</th>';
			table += '</tr>';
			$.each(data.list, function(index, items){
				//alert(items);//[object Object] - 결과로 javascript 객체가 나옴.
				//alert(items.name);//리스트에 있는 이름들이 하나씩 나옴
				table += '<tr>';
				table += '<td align=center>' + items.name + '</td>';
				table += '<td align=center>' + items.id + '</td>';
				table += '<td align=center>' + items.pwd + '</td>';
				table += '</tr>';
				
			});//for 문 - data안에 들어있는 list의 JSON배열에서 1개의 JSON 객체씩 빼서 items로 들어간다. index 시작은 0 번부터
			table += '</table>';
			$('#display').append(table);//append - 추가 */
			
			//방법 2
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.name
				})).append($('<td/>',{
					align: 'center',
					text: items.id
				})).append($('<td/>',{
					align: 'center',
					text: items.pwd
				})).appendTo('#table'); 
			});//tr태그 안으로 td 태그를 3번 append 해라. append($('<td/>',{ })) - td 태그의 객체에 대해 {코드} 를 적용한다.
			//appendTo('#table');는 jquery를 사용하지 않고 id 가 table인 속성에게 추가하라고 하였다. 
		},
		error: function(err){
			console.log(err);
		}
	});
});

/* data는 실어갈게 없으니까 생략가능 */
/* alert(JSON.stringify(data)); - 제이슨형태로 그대로 찍어라 */
 
</script>
</body>
</html>