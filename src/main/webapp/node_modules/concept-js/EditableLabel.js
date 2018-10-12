(function ($) {
	 
	var inputString = '<input type="text" class="editable" style="display: none;" class="form-control"></input>'; 
	
    $.fn.editable = function(key) {
    	
    	return this.each(function() {
	    	var instance = this;
	    	var labelText = $(instance).html().trim();
	    	
	    	$(inputString).insertAfter(this);
	    	
	    	var input = $(this).next();
	    	$(input).val(labelText);
	    	$(input).parent().addClass('input-group');
	    	
	    	$(this).unbind();
	    	if (labelText.length > 0){
	    		bindEvent(instance, input);
	    	} else {
	    		$(instance).hide(function(){
	    			$(input).show();
	    		});
	    	}
	    	
	    	$(input).keyup(function(){
	    		//we only bind if it's not bound
	    		bindEvent(instance, input);
	    	});
	    	
	        return this;
    	});
    };
 
    bindEvent = function(label, input){
    	$(label).unbind();
    	$(input).unbind();
    	
    	var labelText = $(label).html().trim();
    	
    	$(label).hover(function(){
    		$(label).hide();
    		$(input).show();
    		$(input).focus();
    		
    	});
    	
    	$(input).hover(function(){
    		
    	}, function(){
    		$(label).html($(input).val().trim());
    		$(input).hide();
    		$(label).show();
    	});
    	
    	if (labelText.length == 0){
    		$(label).hide(function(){
    			$(input).show();
    		});
    	}
    };
    
}( jQuery ));