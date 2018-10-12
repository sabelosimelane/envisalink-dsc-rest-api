<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-xs-12">
     <div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>${ticket.subject }</h5>
         </div>
         <div class="ibox-content">
         		
         		 <form id="thisform" class="form-horizontal">
				     <input name="id" type="hidden" value="${ticket.id }"></input>
				     <div id="assignTicket" class="form-group"><label class="col-xs-2 control-label">Assign to</label>
				         <div class="col-xs-10">
				         	<select name="assigneeId" data-placeholder="Choose Assignee" class="chosen-select form-control" required value="${ticket.assignee.id }">
								<c:forEach var="user" items="${users }">
									<option value="${user.id }">${user.name}</option>
								</c:forEach>
							</select>
				         </div>
				     </div>
				    <div class="form-group">
				     	<div class="col-xs-12">
					     	<textarea name="comment" class="form-control" placeholder="Write comment..." required></textarea>
					     </div>
					 </div>
				     <div class="form-group">
				         <div class="col-xs-offset-8 col-xs-4">
				               	<button id="submitBtn" class="btn btn-outline btn-success pull-right" type="button">Submit</button>
				               	<span class="pull-right p-xxs"></span>
				               	<button class="btn btn-outline btn-white pull-right" data-dismiss="modal">Cancel</button>
				            </div>
				     </div>
				 </form>
         		          
          </div>
     </div>
 </div>
 
 <script>
 
 Tickets.prototype.reassign = function(form){
	nav.call('SubmitAssignTicket.nav', form, function(resp){
		page.hideModal();
		tickets.view(form.id);
	});
 }
 
 $("#submitBtn").click(function(){
	 	$("#thisform").submit();
	});
		
	$("#thisform").validate({
		submitHandler: function(form) {
			tickets.reassign($("#thisform").serializeFormJSON($("#thisform")));			
		}
	});	
	
</script>	

	
	
	
	
	
	
	
	