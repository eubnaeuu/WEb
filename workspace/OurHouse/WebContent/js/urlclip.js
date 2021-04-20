function clipboard(){
    var url = window.location.href;

    document.getElementById("myInput").value = url;

    var copyText = document.getElementById("myInput");
    copyText.select();
    document.execCommand("Copy");

    $('#clip--meassage').fadeIn(1000);
    $('#clip--meassage').fadeOut(1000);
}