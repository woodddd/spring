//로그인
$(document).keydown(function(key) {
	if(key.keyCode == 13){
		$('#loginBtn').trigger('click');
		$(document).click(function(){
		});
	}
});

$('#loginBtn').click(function(){
	$('#loginIdDiv').empty();
	$('#loginPwdDiv').empty();
   
	if($('#loginId').val()==''){
		$('#loginIdDiv').text('아이디를 입력')
					.css('color','red')
					.css('font-size','8pt')
					.css('font-weight','bold');
      
	}else if($('#loginPwd').val()==''){
		$('#loginPwdDiv').text('비밀번호를 입력')
      				.css('color','red')
      				.css('font-size','8pt')
      				.css('font-weight','bold');
   
	}else{
		$.ajax({
			type : 'post',
			url: '/spring/member/login',
			data: {'id': $('#loginId').val(), 'pwd': $('#loginPwd').val()},
			dataType: 'text',
			success: function(data){
				if(data == 'success'){
					location.href='/spring/index.jsp';
				}else if(data == 'fail'){
					alert('로그인 실패');
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

//로그인 모달
$('.loginForm').click(function(){
	$('.loginModal').show();
	$('.loginModal').on('scroll touchmove mousewheel', function(e){
		e.preventDefault();
		e.stopPropagation(); 
		return false;
	});
});

$('.close').click(function(){
	$('.loginModal').hide();
	$('.loginModal').off('scroll touchmove mousewheel');
});

$(document).click(function(e){
	if($('.loginModal').is(e.target)){
		$('.loginModal').hide(); 
		$('.loginModal').off('scroll touchmove mousewheel');
	}
});

