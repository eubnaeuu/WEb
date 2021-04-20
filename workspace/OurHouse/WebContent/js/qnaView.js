
$('.report-board').click(function(){
    $('.modal-container').fadeIn(100);
    $('.popup-title').text("게시글 신고 사유를 선택해주세요");

    $('.btn-cancel').click(function(){
            $('.modal-container').fadeOut(100);
    })
})

$('.report-comment').click(function(){
    $('.modal-container').fadeIn(100);
    $('.popup-title').text("댓글 신고 사유를 선택해주세요");

    $('.btn-cancel').click(function(){
            $('.modal-container').fadeOut(100);
    })
})

