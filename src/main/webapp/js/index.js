function talk() {
    // alert("哈哈")
    document.getElementById("learn").style.display = "none";
    document.getElementById("talkButton").style.display = "none";
    document.getElementById("talk").style.display = "block";
    document.getElementById("learnButton").style.display = "block";
    document.getElementById("change").style.display = "block";
    document.getElementById("makeSure").style.display = "block";
}
function learn() {
    // alert("哈哈")
    document.getElementById("learn").style.display = "block";
    document.getElementById("talkButton").style.display = "block";
    document.getElementById("talk").style.display = "none";
    document.getElementById("learnButton").style.display = "none";
    document.getElementById("change").style.display = "none";
    document.getElementById("makeSure").style.display = "none";
}
function findQuestions() {
    var word = document.getElementById("say").value;

    $.ajax({
        url: 'jarvers/getSimilerQuestions?questionWord=' + word,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            debugger
            var shows="";
           var list= data.list;
            for (var i=0;i<list.length;i++){

                shows+="</br>"+data.list[i].words;
            }
            $("#bac").innerHTML=shows;
            // document.getElementById("bac").innerHTML =;

        }
    });

}
//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
function answer() {
    var word = document.getElementById("say").value;
    $.ajax({
        url: 'jarvers/talk?listenContent=' + word,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            debugger
            if(!isEmpty(data.fileId)){
                var  a=document.getElementById("fileDownload")
                a.innerHTML=data.fileName;
                a.href="jarvers/download/"+data.fileId+"?fileName="+data.fileName
            }
            document.getElementById("bac").innerHTML = data.back;
            // if (data != null && data.data != null && data.data.length > 0) {
            //     $.each(data.data, function (i, v) {
            //         self.list.push(v);
            //     })
            // }
            // Vue.nextTick(function () {
            //     var box = $('#roll-box');
            //     var paddingTop = content.css('top');
            //     initTop = parseInt(paddingTop);
            //     topCss = initTop;
            //     boxHeight = box.height();
            //     contentHeight = content.height();
            //     self.startRoll();
            // })
        }
    });

}
function changed() {
    var word = document.getElementById("say").value;
    $.ajax({
        url: 'jarvers/changeAnother?listenContent=' + word,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            document.getElementById("bac").innerHTML = data.back;
            // if (data != null && data.data != null && data.data.length > 0) {
            //     $.each(data.data, function (i, v) {
            //         self.list.push(v);
            //     })
            // }
            // Vue.nextTick(function () {
            //     var box = $('#roll-box');
            //     var paddingTop = content.css('top');
            //     initTop = parseInt(paddingTop);
            //     topCss = initTop;
            //     boxHeight = box.height();
            //     contentHeight = content.height();
            //     self.startRoll();
            // })
        }
    });

}
function makeSure() {
    $.ajax({
        url: 'jarvers/isThis',
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data.back == "OK") {
                alert("好了，可以了")
            } else {
                alert("你的电脑还有5秒钟爆炸")
            }

        }
    });
}