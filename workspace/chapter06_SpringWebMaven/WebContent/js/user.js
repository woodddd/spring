$('#writeBtn').click(function(){
   $('#nameDiv').empty();
   $('#idDiv').empty();
   $('#pwdDiv').empty();
   
   if($('#name').val()=='')
      $('#nameDiv').text('이름을 입력하세요');
   else if($('#id').val()=='')
      $('#idDiv').text('아이디를 입력하세요');
   else if($('#pwd').val()=='')
      $('#pwdDiv').text('비밀번호를 입력하세요');
   else
	   //document.writeForm.submit(); - name 속성을 이용
	   //$('#writeForm').submit(); //- jquery 객체 id속성을 이용
   	// . 은 class , # 은 id , name 은 아무것도 없음
   		//$('form[name=writeForm]').submit(); // jquery 객체 name속성 이용
   		//위 3개는 모두 동일한 명령.
	   $.ajax({
		   type: 'post',
		   url: 'write',
		   data: {'name': $('#name').val(),
		   		 'id': $('#id').val(),
		   		 'pwd': $('#pwd').val()}
		   ,
		   success: function(){
			   alert("회원 등록 완료");
		   },
		   error: function(){
			   alert("회원 등록 실패");
		   }
	   }); 
});

$('#id').focusout(function(){ /*포커스가 들어왔다가 나갈 시,*/
   $('#idDiv').empty();
   
   if($('#id').val()==''){
      $('#idDiv').text('필수정보 입력');
      $('#idDiv').css('color', 'magenta');
   }else{
	   
   }
      
});

$('#checkIdBtn').click(function(){
	$('#idDiv').empty();
	   
	   if($('#id').val()==''){
	      $('#idDiv').text('필수정보 입력');
	      $('#idDiv').css('color', 'magenta');
	   }else{
		   $.ajax({
			   type: 'post',
			   url: 'checkId',
			   data: 'id='+$('#id').val(),
			   dataType: 'text', 
			   success: function(result){
				   if(result == '사용 가능'){
					   $('#idDiv').text(result);
					   $('#idDiv').css('color','blue');
					   $('#idDiv').css('font-size','8pt');
					   $('#idDiv').css('font-weight','bold');
				   }else if(result == '사용 불가능'){
					   $('#idDiv').text(result);
					   $('#idDiv').css('color','red');
					   $('#idDiv').css('font-size','8pt');
					   $('#idDiv').css('font-weight','bold');
				   }
				   
			   },error: function(){
				   alert("실패");
			   }
			   
		   });
	   }
});
//dataType - //리턴되는 값의 타입을 적어준다. - 사용가능/ 사용불가능 type종류는 'xml','json','html','text' 아쉬운점은 객체(클래스타입)를 받을 수가 없다.
// 클라이언트가 요청을 해서 @ResponseBody 에서 value를 매핑한 후, 서블릿을 거치지 않고 다시 클라이언트에게 돌아오는데
//만약 정상적으로 돌아오지 못하면 error, 정상적으로 돌아온다면 success가 된다.







