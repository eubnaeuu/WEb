// 상단 메뉴 고정

var $header = $('.section--header');
var $subheader = $('.sub-section');

$(window).scroll(function(){
    $subheader.removeClass('activesub');

    if($(this).scrollTop() > 130){
        $header.addClass('sticky');
        $subheader.addClass('sub--sticky');
        $('.sub--sticky').hide();
        
        $('.sticky').mouseover(function(){
            $('.sub--sticky').show();
        })
        $('.sub--sticky').mouseleave(function(){
            $('.sub--sticky').hide();
        })
        
    }
    else{
        $('.sub--sticky').show();
        $header.removeClass('sticky');
        $subheader.removeClass('sub--sticky');
    }

});
