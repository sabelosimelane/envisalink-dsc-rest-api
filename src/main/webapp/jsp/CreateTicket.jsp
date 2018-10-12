<div class="col-xs-12">
     <div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>Create new ticket</h5>
         </div>
         <div class="ibox-content">
         		<jsp:include page="ticket/ticketform.jsp"></jsp:include>            
          </div>
     </div>
 </div>
  
<script>
var numoffiles=2;
var count=2;

Tickets.prototype.upload = function(input){
	var file = $(input)[0].files[0];
    var upload = new FileUpload('/Pollux/Upload',file);
    
    // maby check size or type here with upload.getSize() and upload.getType()

    // execute upload
    upload.doUpload(function(){
    	count++;
    	if (count === numoffiles){
    		tickets.create($("#thisform").serializeFormJSON($("#thisform")));
    	}
    	
    }, function(){
    	alert('error occured');
    }, function(progress){
    	console.log('progress: ' + progress+'%');
    });
} 
 
$(function(){
		
	$("#submitBtn").click(function(){
	 	$("#thisform").submit();
	});
		
	$("#thisform").validate({
		submitHandler: function(form) {
			if ($('#attachment1').val()){
				--count;
				tickets.upload($('#attachment1'));
			}
			if ($('#attachment2').val()){
				--count;
				tickets.upload($('#attachment2'));
			} 
			
			if (count === numoffiles){
	    		tickets.create($("#thisform").serializeFormJSON($("#thisform")));
	    	}
			
		}
	});	
});
</script>