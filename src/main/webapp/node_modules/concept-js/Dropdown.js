/**** Extends Chosen Plugin Dropbox  ******/

jQuery.fn.extend({
	
	chosen_add : function(value, description, update){
		var instance = this;
		$(instance).append('<option value="'+ value +'" class="added">'+ description +'</option>');
		if (update) $(instance).trigger("chosen:updated");
		return instance;
	},
	
	chosen_setval : function(value){
		var instance = this;
		$(instance).val(value).trigger("chosen:updated");
		return instance;
	},
	
	chosen_clear : function(value){
		var instance = this;
	
		$(instance).find('option.added').remove();
		return instance;
	},
	
	chosen_size : function(){
		var instance = this;
		return $(instance).closest('.chosen-select').next().find(".chosen-results li.active-result").size();
	}
	
});


(function ( $ ) {
	 
    $.fn.greenify = function() {
        this.css( "color", "green" );
        return this;
    };
 
}( jQuery ));