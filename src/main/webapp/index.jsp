<!DOCTYPE html>
<html>

<head>
    <title>Ats Test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">ATS Test</a>
        </div>
    </div>
</nav>

<div class="container" style="min-height: 500px">

    <div class="starter-template">
        <h1>Dutch ATMs Point</h1>
		
		<div class="alert alert-success" id="message"><strong>Loading data ...</strong></div>
		
		<form class="form-horizontal" id="search-form">
            <div class="form-group form-group-lg">
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="text"/>
                </div>
                <button type="submit" id="bth-search"
                            class="btn btn-primary btn-lg">Search
                </button>
            </div>
        </form>
		
        <div id="feedback"></div>

    </div>

</div>

<script type="text/javascript"
        src="webjars/jquery/2.2.4/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function () 
{
	fire_ajax_list()

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        var message = "<strong>searching ...</strong>";
        $('#message').html(message);
        
        fire_ajax_search();

    });

});

function fire_ajax_list() 
{

	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "rest/atm/list",
        cache: false,
        timeout: 600000,
        success: function (data) {

        	var message = "<strong>" + JSON.stringify(data.message, null, 4) + "</strong>";
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data.result, null, 4) + "</pre>";
            
            $('#feedback').html(json);
            $('#message').html(message);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<strong>"+ e.responseText + "</strong>";
            $('#message').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", true);

        }
    });

}


function fire_ajax_search() {

	var search = {}
    search["text"] = $("#text").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "rest/atm/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

        	var message = "<strong>" + JSON.stringify(data.message, null, 4) + "</strong>";
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data.result, null, 4) + "</pre>";
            
            $('#feedback').html(json);
            $('#message').html(message);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<strong>"+ e.responseText + "</strong>";
            $('#message').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}
</script>

</body>
</html>
