<!DOCTYPE html>
<html>

<head>
	
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Pollux | Login</title>
<link rel="icon" type="image/jpg" href="img/favicon.jpg">
<jsp:include page="includes/css-lib.jsp"></jsp:include> 
</head>

<body class="white-bg">
	<jsp:include page="includes/modal.jsp"></jsp:include>
	
	<div id="stage" class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<div class="col-xs-12 clickable" onclick="window.location = '/'">
	     			<img alt="" src="img/monterrey.png" width="100%"> 	
	        </div>
			<form id="loginForm" class="m-t" role="form">
				<div class="form-group">
					<input id="email" type="email" class="form-control"
						placeholder="Username" required="">
				</div>
				<div class="form-group">
					<input id="password" type="password" class="form-control"
						placeholder="Password" required="">
				</div>
				<button id="loginBtn" class="btn btn-success block full-width m-b" style="background-color: #8592b8">Sign
					In</button>
				<a id="forgotPassword" href="#"><small>Forgot password?</small></a>
			</form>
			<p class="m-t">
				<small><strong>Copyright</strong> Ligabazi Software &copy; 2018</small>
			</p>
		</div>
	</div>

	<jsp:include page="includes/javascript-lib.jsp"></jsp:include>
	
	<script>
	var app = new App({root: '/Pollux/', context: '/Pollux/Controller'});
	app.init();
	var message = new Message({timeOut: 3500, positionClass:'toast-top-right'});
	var nav = new Navigator();
	var page = new Page();
	
		$(function() {
			
			$("#loginBtn").click(function() {
	
				nav.call('Login.nav', {
					email : $("#email").val(),
					password : $('#password').val()
					}, function(resp) {
					console.log(resp);
					window.location = '/Pollux/main.jsp';
				});  
				
				return false;
			});
	
			$('#forgotPassword').click(function(){
				
				nav.loadJSP('jsp/forgot-password.jsp', function(resp){
					var props = {};
					props.size = 'medium';
					page.showModal(resp, function() {
						page.init();
				 		}, props);
				});
				
				return false;
			});
		});
		
				
	</script>

</body>

</html>
