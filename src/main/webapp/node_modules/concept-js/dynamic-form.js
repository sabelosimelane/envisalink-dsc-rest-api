(function( $ ) {
 
	$.fn.dynamicform = function(config) {

		var dynamicform = $(this);
		var callback = config.callback;
		
		$(dynamicform).find('input[name]').blur(function(){
			callback(dynamicform);
		});
		
		$(dynamicform).find('select').chosen().change(function(event){
			callback(dynamicform);
		});
        return this;
    };
    
    
   /* $.fn.trigger = function(action, param) {
        
		if ( action === "showpopup") {

        }
 
        if ( action === "hidepopup" ) {
        
        }
        
        return this;
    };*/
    
    $.fn.formtojson = function(form) {
    	var instance = this;
    	
       var o = {};
    	if (form){
    		o = form;
    	}
    	
    	$(instance).find('input[name]').each(function(){

 		   if (o[this.name]) {
 	    	   if (!o[this.name].push) {
 	               o[this.name] = [o[this.name]];
 	           }
 	           o[this.name].push(this.value || '');
 	       } else if (this.value.trim()) {
 	           o[this.name] = this.value.trim() || '';
 	       }
 	   });
    	
    	$(instance).find('select[name]').each(function(){
    		if (this.value.trim()){
    			o[$(this).attr('name')] = $(this).val();
    		}
  	   });
	        
        return o;
    };
 
}( jQuery ));