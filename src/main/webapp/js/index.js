function showMap() {
    // app.title = '北京公交路线 - 百度地图';
    var myChart = echarts.init(document.getElementById('main'));
    $.get('data/bus-line.json', function(data) {
        var busLines = [].concat.apply([], data.map(function (busLine, idx) {
            debugger
            var prevPt;
            var points = [];
            for (var i = 0; i < busLine.length; i += 2) {
                var pt = [busLine[i], busLine[i + 1]];
                if (i > 0) {
                    pt = [
                        prevPt[0] + pt[0],
                        prevPt[1] + pt[1]
                    ];
                }
                prevPt = pt;

                points.push([pt[0] / 1e4, pt[1] / 1e4]);
            }
            return {
                coords: points
            };
        }));
        myChart.setOption(option = {
            bmap: {
                center: [116.46, 39.92],
                zoom: 10,
                roam: true,
                mapStyle: {
                    styleJson: [{
                        'featureType': 'water',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'land',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#f3f3f3'
                        }
                    }, {
                        'featureType': 'railway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fdfdfd'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry.fill',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'poi',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'green',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'subway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'manmade',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'local',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'boundary',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'building',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'label',
                        'elementType': 'labels.text.fill',
                        'stylers': {
                            'color': '#999999'
                        }
                    }]
                }
            },
            series: [{
                type: 'lines',
                coordinateSystem: 'bmap',
                polyline: true,
                data: busLines,
                silent: true,
                lineStyle: {
                    normal: {
                        color: '#c23531',
                        color: 'rgb(200, 35, 45)',
                        opacity: 0.2,
                        width: 1
                    }
                },
                progressiveThreshold: 500,
                progressive: 200
            }]
        });
    });
}
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
                document.getElementById("bac").innerHTML = data.back;
            }else if(!isEmpty(data.fileList)){
                var list=data.fileList;
                debugger

                for (var  i=0;i<list.length;i++){
                    var  a=document.createElement("a");
                    a.innerHTML=list[i].fileName
                    a.href="jarvers/download/"+list[i].fileId+"?fileName="+list[i].fileName
                    document.getElementById("fileDownload").appendChild(a)
                    document.getElementById("fileDownload").appendChild(document.createElement("br"))
                }
            }
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