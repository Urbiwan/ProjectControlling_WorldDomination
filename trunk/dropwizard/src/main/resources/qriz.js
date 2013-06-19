function InternData (name){
    this.Name = name;
    this.Month = [];
    this.WorkAcc = [];
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
    var inspectedEmployeeIds = [];
    var datasets = [];

    for(var i = 0; i < data.Entries.length; i++) {
        if(!contains(inspectedEmployeeIds, data.Entries[i].MaId)){
            var dataset = new InternData(data.Entries[i].Employee);
            var currentEmployee = data.Entries[i].MaId;

            datasets.push(dataset);
            inspectedEmployeeIds.push(currentEmployee);

            for(var j = 0; j < data.Entries.length; j++) {
                if(currentEmployee == data.Entries[j].MaId) {
                    var monthIndex = containsGetIndex(dataset.Month, data.Entries[j].Month);
                    if(monthIndex == -1) {
                        dataset.Month.push(data.Entries[j].Month);
                        dataset.WorkAcc.push(0);
                        monthIndex = dataset.Month.length - 1;
                    }

                    dataset.WorkAcc[monthIndex] = dataset.WorkAcc[monthIndex] + data.Entries[j].Hours;
                }
            }
        }
    }

    convertMonthToName(datasets);
    createCharts(datasets);
}

function convertMonthToName(datasets) {
    for(var i = 0; i < datasets.length; i++) {
        for(var j = 0; j < datasets[i].Month.length; j++) {
            if(datasets[i].Month[j] == 0){
                datasets[i].Month[j] = "";
            }
            else if(datasets[i].Month[j] == 1){
                datasets[i].Month[j] = "Januar";
            }
            else if(datasets[i].Month[j] == 2){
                datasets[i].Month[j] = "Februar";
            }
            else if(datasets[i].Month[j] == 3){
                 datasets[i].Month[j] = "Maerz";
            }
            else if(datasets[i].Month[j] == 4){
                datasets[i].Month[j] = "April";
            }
            else if(datasets[i].Month[j] == 5){
                datasets[i].Month[j] = "Mai";
            }
            else if(datasets[i].Month[j] == 6){
                datasets[i].Month[j] = "Juni";
            }
            else if(datasets[i].Month[j] == 7){
                datasets[i].Month[j] = "Juli";
            }
            else if(datasets[i].Month[j] == 8){
                datasets[i].Month[j] = "August";
            }
            else if(datasets[i].Month[j] == 9){
                datasets[i].Month[j] = "September";
            }
            else if(datasets[i].Month[j] == 10){
                datasets[i].Month[j] = "Oktober";
            }
            else if(datasets[i].Month[j] == 11){
                datasets[i].Month[j] = "November";
            }
            else if(datasets[i].Month[j] == 12){
                datasets[i].Month[j] = "Dezember";
            }
        }
    }
}

function createCharts(datasets) {
    //<canvas id="myChart" width="400" height="400"></canvas>
    for(var i = 0; i < datasets.length; i++) {
        var p = document.createElement("p");
        var div = document.createElement("div");
        var canvas = document.createElement("canvas");
        var ctx = canvas.getContext("2d");

        document.getElementById('GRAPHICS').appendChild(div);
        p.innerHTML = datasets[i].Name;
        div.appendChild(p);
        div.appendChild(canvas);
        canvas.setAttribute("id", "myChart"+i);
        canvas.setAttribute("width", "400");
        canvas.setAttribute("height", "400");

        var data = {
        	labels : datasets[i].Month,
        	datasets : [
        		{
        			fillColor : "rgba(220,220,220,0.5)",
        			strokeColor : "rgba(220,220,220,1)",
        			pointColor : "rgba(220,220,220,1)",
        			pointStrokeColor : "#fff",
        			data : datasets[i].WorkAcc
        		}
        	]
        };

        var options = {

        	//Boolean - If we show the scale above the chart data
        	scaleOverlay : false,

        	//Boolean - If we want to override with a hard coded scale
        	scaleOverride : false,

        	//** Required if scaleOverride is true **
        	//Number - The number of steps in a hard coded scale
        	scaleSteps : datasets[i].Month.length,
        	//Number - The value jump in the hard coded scale
        	scaleStepWidth : 1,
        	//Number - The scale starting value
        	scaleStartValue : 0,

        	//String - Colour of the scale line
        	scaleLineColor : "rgba(0,0,0,.1)",

        	//Number - Pixel width of the scale line
        	scaleLineWidth : 1,

        	//Boolean - Whether to show labels on the scale
        	scaleShowLabels : false,

        	//Interpolated JS string - can access value
        	scaleLabel : "<%=value%>",

        	//String - Scale label font declaration for the scale label
        	scaleFontFamily : "'Arial'",

        	//Number - Scale label font size in pixels
        	scaleFontSize : 12,

        	//String - Scale label font weight style
        	scaleFontStyle : "normal",

        	//String - Scale label font colour
        	scaleFontColor : "#666",

        	///Boolean - Whether grid lines are shown across the chart
        	scaleShowGridLines : true,

        	//String - Colour of the grid lines
        	scaleGridLineColor : "rgba(0,0,0,.05)",

        	//Number - Width of the grid lines
        	scaleGridLineWidth : 1,

        	//Boolean - Whether the line is curved between points
        	bezierCurve : true,

        	//Boolean - Whether to show a dot for each point
        	pointDot : true,

        	//Number - Radius of each point dot in pixels
        	pointDotRadius : 3,

        	//Number - Pixel width of point dot stroke
        	pointDotStrokeWidth : 1,

        	//Boolean - Whether to show a stroke for datasets
        	datasetStroke : true,

        	//Number - Pixel width of dataset stroke
        	datasetStrokeWidth : 2,

        	//Boolean - Whether to fill the dataset with a colour
        	datasetFill : true,

        	//Boolean - Whether to animate the chart
        	animation : true,

        	//Number - Number of animation steps
        	animationSteps : 60,

        	//String - Animation easing effect
        	animationEasing : "easeOutQuart",

        	//Function - Fires when the animation is complete
        	onAnimationComplete : null

        };

        new Chart(ctx).Line(data/*,options*/);
    }
}

function contains(collection, element) {
    var found = false;
    for(var i = 0; i < collection.length; i++) {
        if(collection[i] == element){
            found = true;
            break;
        }
    }

    return found;
}

function containsGetIndex(collection, element) {
    for(var i = 0; i < collection.length; i++)
        if(collection[i] == element)
            return i;

    return -1;
}

function response(data) {
    $(document.getElementById('RESPONSE')).show('slow');
	document.getElementById('actifity').innerHTML = data.actifity;
	document.getElementById('faktActivity').innerHTML = data.faktActivity;
	document.getElementById('efficiency').innerHTML = data.efficiency;
	document.getElementById('totalQuantity').innerHTML = data.totalQuantity;
	document.getElementById('costs').innerHTML = data.costs;
	document.getElementById('benefit').innerHTML = data.benefit;
	document.getElementById('illnessRate').innerHTML = data.illnessRate;
}

window.onload = function(){
    $(document.getElementById('RESPONSE')).hide(0);
}


