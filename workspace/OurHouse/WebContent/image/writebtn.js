$('.btn-write').click(function(e){
    $('.dropdown-write').show();

    $('body').click(function(e){
    if(!$(e.target).hasClass("btn-write")){
        $('.dropdown-write').hide();
    }
})
})