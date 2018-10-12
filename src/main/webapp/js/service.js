function Service(){
	
	this.nav = new Navigator();
}

Service.prototype.fetchLabels = function(success, error){

	this.nav.call('FetchLabels.nav', {}, success, error);
}

Service.prototype.fetchSnippets = function(label, success) {
	
	this.nav.call('FetchSnippets.nav', {label: label}, success, function(){
		console.log('Could not fetch snippets...');
	});
}