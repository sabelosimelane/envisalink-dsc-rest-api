function Helper(){
	
}

Helper.prototype.parseJSON = function(string){
    
	if (jQuery.type( string) == 'array'){
		string = JSON.stringify(string);
		console.log('stringifying...');
	}
	
	var temp = string.toString().replace(new RegExp('=', 'g'), ':');
 	var b = $.parseJSON( RJSON.transform(temp));
	return b;
};

Helper.prototype.parseJSONToArray = function(array){
	var capabilities = this.parseJSON(array);
	var cArray = [];	 	
 	for (var i=0; i<capabilities.length; i++){
 		cArray[i] = capabilities[i].id;
 	}
	return cArray;
}