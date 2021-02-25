//회원가입
$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	
	//if($('#name').val()==''){
	if($('input[id=name]').val()==''){//위와 동일한 방식
		$('#nameDiv').text('이름을 입력해주세요.')
		$('#nameDiv').css('color','red');
		$('#nameDiv').css('font-size','8pt');
		$('#nameDiv').css('font-weight','bold');
      
	}else if($('#id').val()==''){
		$('#idDiv').text('아이디를 입력해주세요')
		$('#idDiv').css('color','red');
		$('#idDiv').css('font-size','8pt');
		$('#idDiv').css('font-weight','bold');
      
	//}else if($('#pwd').val()==''){ id속성을 생성하지 않고 name 으로 하는 방법
	}else if($('#pwd').val()==''){//$('태그명[name=변수명]')
		$('#pwdDiv').text('비밀번호를 입력해주세요')
		$('#pwdDiv').css('color','red');
		$('#pwdDiv').css('font-size','8pt');
		$('#pwdDiv').css('font-weight','bold');
		
	}else if($('#repwd').val()!=$('#pwd').val()){
		$('#repwdDiv').text('비밀번호가 맞지 않습니다')
		$('#repwdDiv').css('color','red');
		$('#repwdDiv').css('font-size','8pt');
		$('#repwdDiv').css('font-weight','bold');
	}else if($('#id').val() != $('#check').val()){
		$('#idDiv').text('중복체크를 해주세요')
		$('#idDiv').css('color','red');
		$('#idDiv').css('font-size','8pt');
		$('#idDiv').css('font-weight','bold');
	}else{
	
		$('form[name=writeForm]').submit();
	}
});

//중복 아이디 체크.(중복체크버튼 제거했음)
$('#id').focusout(function(){
	if($('#id').val()==''){
		$('#idDiv').text('먼저 아이디를 입력하세요')
		$('#idDiv').css('color','magenta')
		$('#idDiv').css('font-size','8pt')
		$('#idDiv').css('font-weight','bold');
	
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/member/checkId',
			data: 'id='+$('#id').val(),
			dataType: 'text',
			success: function(data){
				if(data == 'exist'){
					$('#idDiv').text('사용 불가능')
					$('#idDiv').css('color','magenta')
					$('#idDiv').css('font-size','8pt')
					$('#idDiv').css('font-weight','bold');
					
				}else if(data == 'non_exist'){
					$('#check').val($('#id').val());					
					
					$('#idDiv').text('사용 가능')
					$('#idDiv').css('color','blue')
					$('#idDiv').css('font-size','8pt')
					$('#idDiv').css('font-weight','bold');
				}
			}
		});
	}
});

//우편번호

$('#checkPostBtn').click(function(){
	window.open("/spring/member/checkPost","zipcode","width=700 height=500 scrollbars=yes");
});

$('#checkPostSearchBtn').click(function(){
	$.ajax({
		type: 'post',
		url: '/spring/member/checkPostSearch',
		data: $('#checkPostForm').serialize() ,
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			$('#checkPostTable tr:gt(2)').remove();
			
			$.each(data.list,function(index,items){
				var address = items.sido+' '
							+ items.sigungu+' '
							+ items.yubmyundong+' '
							+ items.ri+' '
							+ items.roadname+' '
							+ items.buildingname;
				
				address = address.replace(/null/g, '') //정규식 표현법 !  -  null이라는 글자를 전체에서 찾아서 ''으로 변환하겠다.
				
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.zipcode
					
				})).append($('<td/>',{
					colspan: '3',
					}).append($('<a/>',{
						href: '#',
						id: 'addressA',
						text: address
					}))
				).appendTo($('#checkPostTable'));
			});//each
			
			$('a').click(function(){//a태그가 클릭될때,
				//this - a 태그중 클릭된 a 태그
				//alert($(this).prop('tagName'));
				
				//prop - javascript property를 취급한다 
				//선택된 요소의 속성과 값을 설정하거나 리턴합니다.
				//$(this).parent() => td
				
				//alert($(this).parent().prev()); - > 부모노드의 td중 이전 td의 값
				
				//alert($(this).parent().prev().text()); // 위의 태그 값찍기.
				
				$('#postcode', opener.document).val($(this).parent().prev().text()); //$('#postcode', opener.document) 열려있는 창들중에 id가 postcode인 태그.
				//a태그의 부모태그인 td태그의 이전태그의 text.
				$('#address', opener.document).val($(this).text());//a태그의 텍스트
				$('#detailAddress', opener.document).focus(); //jquery 의 문법을 사용. ajax 는 parentNode 와 같이 사용을 한다.
				window.close();
			});
			
		},
		error: function(err){
			console.log(err);
		}
	});
});//serialize() - id가 checkPostForm 인 태그의 name 변수를 넘겨달라. 'sido='+$('#sido').val() + '&sigungu=' + $('sigungu').val() ... 로 넘어감.


//회원정보 수정
$('#modifyBtn').click(function(){
	$('#nameDiv').empty();
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	
	if($('#name').val()==''){
		$('#nameDiv').text('이름을 입력')
		$('#nameDiv').css('color','red');
		$('#nameDiv').css('font-size','8pt');
		$('#nameDiv').css('font-weight','bold');
      
	}else if($('#pwd').val()==''){//$('태그명[name=변수명]')
		$('#pwdDiv').text('비밀번호를 입력')
		$('#pwdDiv').css('color','red');
		$('#pwdDiv').css('font-size','8pt');
		$('#pwdDiv').css('font-weight','bold');
		
	}else if($('#repwd').val()!=$('#pwd').val()){
		$('#repwdDiv').text('비밀번호가 맞지 않습니다')
		$('#repwdDiv').css('color','red');
		$('#repwdDiv').css('font-size','8pt');
		$('#repwdDiv').css('font-weight','bold');
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/member/modify',
			data: $('#modifyForm').serialize() ,
			success: function(){
				alert('회원정보 수정 완료');
				location.href='/spring/index.jsp';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

