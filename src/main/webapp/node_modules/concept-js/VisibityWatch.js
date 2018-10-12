function VisibleWatch(){
	
}

VisibleWatch.prototype.on = function(elem, trigger, callback){
	var instance = this;
	
	if (trigger === 'visible'){
		$(elem).each(function(){
			if ($(this).is(':visible')){
				instance.repeat(elem, callback, 2000);
			}
		})
		
	}
}


VisibleWatch.prototype.repeat = function(elem, callback, timeout){
	var instance = this;
	
	var myCallback = setTimeout(function(){
		callback(elem);
		instance.repeat(elem, callback, timeout);
	}, timeout);

}