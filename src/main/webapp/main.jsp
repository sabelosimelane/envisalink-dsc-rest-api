<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pollux | Sectional Title</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet"> -->
    <link href="node_modules/@fortawesome/fontawesome-free/css/all.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
	<link href="bower_components/sweetalert/sweetalert.css" rel="stylesheet">
	<link href="bower_components/toastr/toastr.min.css" rel="stylesheet">
	<link href="bower_components/chosen/chosen.css" rel="stylesheet">
	<link href="node_modules/summernote/dist/summernote.css" rel="stylesheet">
	<link href="bower_components/switchery/dist/switchery.min.css" rel="stylesheet">
	
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

            <div>
                <strong>Copyright</strong> Ligabazi &copy; 2018
            </div>
        </div>

        </div>
        </div>



    <!-- Mainly scripts -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="bower_components/metisMenu/jquery.metisMenu.js"></script>
    <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="bower_components/iCheck/icheck.min.js"></script>
    <script type="text/javascript" src="bower_components/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="js/toastr.min.js"></script>
    <script src="bower_components/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="bower_components/moment/min/moment.min.js"></script>
	<script type="text/javascript" src="bower_components/moment/min/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="node_modules/summernote/dist/summernote.min.js"></script>
	<script type="text/javascript" src="bower_components/switchery/dist/switchery.min.js"></script>
	
	<!-- CONCEPT-JS -->
	<script type="text/javascript" src="node_modules/concept-js/app.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/navigator.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Message.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Page.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Search.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/ccalendar.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Dropdown.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/datatable.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/util.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Helper.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/VisibityWatch.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/Redirect.js"></script>
	<script type="text/javascript" src="node_modules/concept-js/FileUpload.js"></script>
	
	<script src="bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
	
    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
	<script type="text/javascript" src="includes/navigation.js"></script>

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
