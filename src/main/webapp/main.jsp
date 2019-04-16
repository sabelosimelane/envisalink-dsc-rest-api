<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pollux | Sectional Title</title>
	<jsp:include page="includes/css-lib.jsp"></jsp:include>
</head>

<body class="top-navigation">
	<jsp:include page="includes/modal.jsp"></jsp:include>
    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom white-bg">
        	<jsp:include page="includes/navigation.jsp"></jsp:include>
        </div>
        <div class="wrapper wrapper-content">
            <div id="stage" class="container">
            </div>
        </div>
        <div class="footer">
            <div><strong>Copyright</strong> Ligabazi &copy; 2019</div>
        </div>
        </div>
        </div>

	<jsp:include page="includes/javascript-lib.jsp"></jsp:include>

 <script>
	var app = new App({root: '/Template/', context: '/Template/Controller'});
	app.init();
	var message = new Message({timeOut: 3500, positionClass:'toast-top-right'});
	var nav = new Navigator();
	var page = new Page();
 	var util = new Util();
 	var fileupload = new FileUpload();
 	
 	$(function(){
 		new Navigation().bind();
 		$('#discussionsNav').click();
 	});
 
 </script>

</body>

</html>
