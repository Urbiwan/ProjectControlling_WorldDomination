function SERVER_IP() {
    return '192.168.100.126:8080';
}

$(function () {
    'use strict';
    $('#fileupload').fileupload({
        url: '/upload',
        dataType: 'json',
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                    'width',
                    progress + '%'
            );
        },
        done: function (e, data) {
            upload(e, data);
        }
    });
});

function upload(e, data) {
    $.ajax({
        type: 'get',
        url: '/download',
        data: 'token='+data.result.token,
        success: draw
    });
    $.ajax({
        type: 'get',
        url: '/compute',
        data: 'token='+data.result.token,
        success: response
    });
}

function draw(data) {
    //TODO
}

function response(data) {
	document.getElementById('actifity').innerHTML = data.actifity;
	document.getElementById('faktActivity').innerHTML = data.faktActivity;
	document.getElementById('efficiency').innerHTML = data.efficiency;
	document.getElementById('totalQuantity').innerHTML = data.totalQuantity;
	document.getElementById('costs').innerHTML = data.costs;
	document.getElementById('benefit').innerHTML = data.benefit;
	document.getElementById('illnessRate').innerHTML = data.illnessRate;
}