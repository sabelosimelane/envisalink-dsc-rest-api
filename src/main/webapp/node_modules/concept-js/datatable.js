(function( $ ) {
 
	$.fn.cdatatable = function(action, htmlcontent) {
		
		return this.each(function() {
			var instance = this;
			
			if ( action == "addContentToHeader") {

				$(instance).closest('.table-responsive').find('.row:first div:first').html(htmlcontent);
	        }
	        return this;
		});
    };
 
}( jQuery ));