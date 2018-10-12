<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pollux | Prelogin</title>
	<link rel="icon" type="image/jpg" href="img/favicon.jpg">
	<jsp:include page="includes/css-lib.jsp"></jsp:include> 
</head>

<body class="fixed-sidebar no-skin-config full-height-layout">
	<jsp:include page="includes/modal.jsp"></jsp:include>
	
	<div id="wrapper">

	        <div class="gray-bg col-sm-6 col-sm-offset-3" style="background: #fff; height: 100% !important; float: left;">
	            <div>

				     <div class="col-xs-10">
				         <img class="logo" src="img/monterrey-logo.png" alt="logo" style="max-height: 250px; display: block;">
				     </div>
			         <div id="stage" class="ibox-content">
			
			             
			         </div>
		    		
	            </div>
	
	        <div class="footer">
	            <div>
	                <strong>Copyright</strong> Snippet Manager &copy; 2018
	            </div>
	        </div>
	
	        </div>
        </div>

	<jsp:include page="includes/javascript-lib.jsp"></jsp:include>
	  <c:if test="${not empty redirect}">
		<jsp:include page="includes/redirect.jsp"></jsp:include>
	</c:if>
	
	
	<script>
	
	function Registration() {};

    var app = new App({root: '/Pollux/', context: '/Pollux/Controller'})	;
    app.init();
    app.cache = {};
    app.currency = 'E';
    moment.updateLocale('en', {
        calendar : {
            lastDay : '[Yesterday at] LT',
            sameDay : '[Today at] LT',
            nextDay : '[Tomorrow at] LT',
            lastWeek : '[last] dddd [at] LT',
            nextWeek : 'dddd [at] LT',
            sameElse : 'DD/MM/YYYY HH:mm'
        }
    });

    var nav = new Navigator();
    var message = new Message({positionClass:'toast-top-right'});

    var page = new Page();
    var util = new Util();
   	var registration = new Registration();
	</script>

    
</body>

</html>