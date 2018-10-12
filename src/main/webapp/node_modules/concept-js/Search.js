function Search(){
	
	/**
	 * The css class that contains the content we are searching. We are going to call html() to retrieve the value.
	 */
	this.targetClass = "";
	
	/**
	 * The class we will hide after doing the filtering
	 */
	this.rowClass = "";
}

/**
 * Will search for matching content and hide the rest if it does not match.
 */
Search.prototype.search = function(searchTerm){
	var list = $("."+ this.targetClass);
	var rowClass = this.rowClass;
	var search = new Search();

	if (searchTerm == ''){
		search.showAllRows(rowClass);
		return;
	}
	
	list = $.grep(list, function(value, index){
		return ($(value).html() != null && $(value).html().trim().toLowerCase().indexOf(searchTerm.toLowerCase()) > -1);
	});
	
	search.hideAllRows(rowClass);
	$(list).each(function(){
		search.showRow($(this), rowClass);
	});
};

Search.prototype.hideRow = function(object, cssClass){
	$(object).closest('.'+cssClass).hide();
};

Search.prototype.showRow = function(object, cssClass){
	$(object).closest('.'+cssClass).show();
};

Search.prototype.hideAllRows = function(rowClass){
	$('.'+rowClass).hide();
};

Search.prototype.showAllRows = function(rowClass){
	$('.'+rowClass).show();
};

Search.prototype.unbind = function(){
	$("#searchTerm").unbind();
	$("#searchTerm").val('');
}