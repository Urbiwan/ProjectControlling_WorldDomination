function readFile(file, callback) {
	var reader = new FileReader();
	reader.onload = function(evt) {
		document.getElementById('status').innerHTML = "Ready";
        if (typeof callback=="function")
            callback(file, evt);
	};
	
	reader.readAsBinaryString( file );
}

window.onload = function() {          
	document.getElementById("btnUpload").onclick = function() {
		var input = document.getElementById("file_input");
	  
		if ( typeof FileReader=="undefined" || !input.files)
		{
			alert("Your browser doesn't support the HTML 5 File API!");
			return;
		}
		if ( input.files.length < 1 )
		{
			alert("Please select a file!");
			return;
		}
		readFile(input.files[0], function(file, evt)
		{
			$.ajax({
				type: 'get',
				//contentType: '*/*',
				url: 'http://192.168.100.126:8080/upload',
				data: 'file='+encodeURIComponent(evt.target.result),
				success: upload
            });
        });
	}
}

function upload(data) {
    $.ajax({
        type: 'get',
        url: 'http://192.168.100.126:8080/compute',
        data: 'token='+data.token,
        success: response
    });
}

function response(data) {
	alert(data.actifity);
}