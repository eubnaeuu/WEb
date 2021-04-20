
function zoomIn() {
    var hover = event.srcElement.previousElementSibling;
    hover.style.transform = "scale(1.08)";
    hover.style.zIndex = 1;
    hover.style.transition = "all 0.5s";
}

function zoomOut() {
    var hover = event.srcElement.previousElementSibling;
    hover.style.transform = "scale(1)";
    hover.style.zIndex = 0;
    hover.style.transition = "all 0.5s";
}