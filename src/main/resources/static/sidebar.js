//헤더에 선언되어 있으면 js파일이 태그가 읽히기 전에 실행 그래서 window...

window.onload = function() {
    var toggle = 1;

    document.getElementById("sidebtn").onclick = function () {
        var sb = document.getElementById('sidebar');
        if(toggle == 0) {
            sb.style.display = "none";
            toggle = 1;
        }
        else {
            sb.style.display = "block";
            toggle = 0;
        }
    }
}
