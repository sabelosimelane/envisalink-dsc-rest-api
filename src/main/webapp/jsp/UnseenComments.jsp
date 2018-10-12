

<script>
function Comments(){}

var comments = new Comments();

Comments.prototype.updateSeen = function(){
	 $('.table-responsive .badge.badge-primary').each(function(){
		if ($(this).text() === '0'){
			$(this).removeClass('badge-primary');
		} 
	 });
	 
	 nav.call('FetchSeenComms.nav', {intValue: commType}, function(resp){
		resp.forEach(function(element) {
			console.log('working with: '+element);   
			$('.table-responsive tr.comm[id="'+ element +'"] .badge').removeClass('badge-primary');
		});
	 });
}

Comments.prototype.recordSeen = function(commid, type){
	nav.call('RecordSeenComms.nav', {commid: commid, typeid: type}, function(resp){
		
	});
}

$(function(){
	comments.updateSeen();
});

</script>