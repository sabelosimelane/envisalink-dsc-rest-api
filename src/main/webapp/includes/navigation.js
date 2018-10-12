function Navigation(){};

Navigation.prototype.bind = function(){

	$('#navigation a.nav').click(function(){
		var type = $(this).attr('nav-type');
		if (type === 'navigator'){
			var value = $(this).attr('nav-value');
			
			nav.call(value, {}, function(resp){
				$('#stage').html(resp);
			});
			
		}
	});
	
	$('#logout').click(function(){
		
		nav.call('Logout.nav', {}, function(){
			window.location = app.root;
		});
		
	});
	
	$('#createticket').click(function(){
		nav.call('Tickets.nav', {}, function(resp){
			$('#stage').html(resp);
			nav.call('CreateTicket.nav', {}, function(resp) {
				var props = {};
				props.size = 'large';
				page.showModal(resp, function() {
					page.init();
				}, props);
			});
		});
	});
	
	$('#createuser').click(function(){
		nav.call('Users.nav', {}, function(resp){
			$('#stage').html(resp);
			nav.loadJSP('jsp/CreateUser.jsp', function(resp) {
				var props = {};
				props.size = 'medium';
				page.showModal(resp, function() {
					page.init();
				}, props);
			});
		});
	});
};
	
