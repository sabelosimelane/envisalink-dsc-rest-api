(function( $ ) {
 
	$.fn.ccalendar = function(action, options) {
        
		if ( action === "showpopup") {
			var calendar = $(this);
			calendar.click(function(){
				calendar.attr('role', 'button');
				calendar.attr('tabindex', '0');
				calendar.popover({
		          	placement: 'top',
		        	html: true,
		        	container: '.wrapper-content',
		        	template :'<div class="widget-text-box popover"><h3 class="font-bold no-margins popover-content">Alex Smith</h3><div class="text-right"><a class="btn btn-xs btn-default"><i class="fa fa-trash"></i> '+ options.button +'</a></div></div>',
		            content: options.title
		        });	
				
				calendar.popover('show');

			});
        }
 
        if ( action === "hidepopup" ) {
        	$(this).popover('hide');
        }
        return this;
    };
 
}( jQuery ));