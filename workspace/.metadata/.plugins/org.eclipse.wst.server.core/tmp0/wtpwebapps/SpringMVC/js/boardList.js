//목록
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoardList',
		data: {'pg' : $('#pg').val()},
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
				})).append($('<td/>',{
					}).append($('<a/>',{
						href: '#',
						text: items.subject,
						id: 'subjectA',
						class: items.seq+''
						
					})).append($('<span/>',{
						text: ' [' + items.reply + ']',
						id: 'replySpan'
						})
					)
				).append($('<td/>',{
					align: 'center',
					text: items.id
				})).append($('<td/>',{
					align: 'center',
					text: items.hit
				})).append($('<td/>',{
					align: 'center',
					text: items.logtime
				})).appendTo($('#boardListTable'));
			
				//답글
				for(i=0; i<items.lev; i++){
					$('.' + items.seq).before('&emsp;'); //클래스명이 items.seq 인태그의 앞으로 넣어라.
				}
				if(items.pseq != 0){//원글이 아닌것은 답글이다.
					$('.' + items.seq).before($('<img/>',{
						src: '../image/reply.gif'
					}));
				}
				
				
				//로그인 여부 체크
				//클래스를 이용하는 방식으로 클래스 버튼을 눌렀을때, 로하게되면 $('#subjectA').click
				//을 사용할 때 처럼 아이디의 중복이 일어나 이벤트가 한번만 발생하게 되는 현상이 없어지게됨.
				//그래서 on 을 이용하거나, 클래스 값을 주어 목록의 모든 a태그에 대해 이벤트가 발생할 수 있도록해줌.
				/*
				$('.'+items.seq).click(function(){
					//alert($(this).prop('tagName'));
					
					if(data.memId == null){
						alert('먼저 로그인해주세요');
					}else{
						//alert($(this).parent().prev().text());
						let seq = $(this).parent().prev().text();
					}
				});
				*/
			});//each
			
			//로그인 여부
			//on을 사용할때엔, 부모(조상이 와도됨.).on(이벤트, 자식, 함수) 와 같은 형식으로 사용해주어야함. - 동기방적방식을 사용할것이다.
			$('#boardListTable').on('click','#subjectA',function(){
				//alert($(this).prop('tagName'));
				
				if(data.memId == null){
					alert('먼저 로그인해주세요');
				}else{
					//alert($(this).parent().prev().text());
					let seq = $(this).parent().prev().text();
					let pg = data.pg;
					location.href = '/spring/board/boardView?seq='+seq+"&pg="+pg;
				}
			});//위와같이 아이디가 모두 동일한 리스트에 대해서 이벤트를 걸어줄 때, 한번 이벤트가 발생하고나면 나머지는 클릭 이벤트가 안먹음(비동적방식이라서그럼). 엄청중요한내용임!
			
			//페이징 처리
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
		
		},
		error: function(err){
			console.log(err);
		}
	});


//검색
$('#boardSearchBtn').click(function(event, str){
	if(str != 'research') $('input[name=pg]').val(1); //직접 검색버튼을 눌렀을 때
	
	if($('#keyword').val() == ''){
		alert('검색어를 입력하세요');
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/board/getBoardSearch',
			data: $('#boardSearchForm').serialize(), //pg, searchType, keyword
			dataType: 'json',
			success: function(data){
				//alert(JSON.stringify(data));
				
				$('#boardListTable tr:gt(0)').remove();
				
				$.each(data.list, function(index, items){
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
						
						}).append($('<a/>',{
							href: '#',
							text: items.subject,
							id: 'subjectA',
							class: items.seq+''
						}))
					
					).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).appendTo($('#boardListTable'));
					
					//답글
					for(i=0; i<items.lev; i++){
						$('.'+items.seq).before('&emsp;');
					}
					if(items.pseq!=0){
						$('.'+items.seq).before($('<img/>',{
							src: '../image/reply.gif'
						}));
					}
				});//each
				
				//페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error: function(err){
				console.log(err);
			}			
		});
	}
});

});