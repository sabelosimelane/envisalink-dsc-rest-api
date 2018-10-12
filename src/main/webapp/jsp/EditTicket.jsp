<div class="col-xs-12">
      <div class="ibox float-e-margins">
          <div class="ibox-title">
              <h5>Update ticket</h5>
          </div>
          <div class="ibox-content">
          		<jsp:include page="ticket/ticketform.jsp"></jsp:include>
          		            
           </div>
      </div>
  </div>
  
<script>

Tickets.prototype.update = function(form){
	 nav.call('UpdateTicket.nav', form, function(resp){
		 tickets.refresh();
		 message.show('Ticket updated successfully.');
		 page.hideModal();
	 });
};

	
	$(function(){
		$('#thisform #assignTicket').removeClass('hide');
		
		$("#submitBtn").click(function(){
			  $("#thisform").submit();
			});
				
			$("#thisform").validate({
				submitHandler: function(form) {
					tickets.update($(form).serializeFormJSON(form));
				}
			});
		
	});
</script>