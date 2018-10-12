 <%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 <form id="thisform" class="form-horizontal">
     <input name="id" type="hidden" value="${ticket.id }"></input>
     <div id="assignTicket" class="form-group hide"><label class="col-xs-2 control-label">Assign to</label>
         <div class="col-xs-10">
         	<select name="assignee" data-placeholder="Choose Assignee" class="chosen-select form-control" required value="${ticket.assignee.id }">
				<c:forEach var="user" items="${users }">
					<option value="${user.id }">${user.name}</option>
				</c:forEach>
			</select>
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Type of Issue</label>
         <div class="col-xs-10">
         	<select name="type" data-placeholder="Choose Ticket Type" class="chosen-select form-control" required value="${ticket.type.id }">
				<option value="1">General</option>
				<option value="2">Billing</option>
				<option value="3">Maintenance</option>
				<option value="4">Security</option>
			</select>
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Unit Concerned</label>
         <div class="col-xs-10">
         	<select name="unit" data-placeholder="Choose Ticket Type" class="chosen-select form-control" required value="${ticket.unit == null? 0 : ticket.unit.id }">
				<option value="0">Not Applicable</option>
				<c:forEach var="unit" items="${units }">
					<option value="${unit.id }">Unit ${unit.number }</option>
				</c:forEach>
			</select>
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Subject</label>
         <div class="col-xs-10"><input name="subject" type="text" placeholder="e.g. Water leakage" class="form-control" required value="${ticket.subject}">
         </div>
     </div>
     <concept:fileupload param1="TICKET" fetchNavigator="FetchFilesForCommunication.nav" fetchParm="{'type': '1', 'communicationid': '${ticket.id}'}" deletenavigator="DeleteAttachment.nav"></concept:fileupload>
     <div class="form-group">
         <div class="col-xs-12"><textarea name="description" class="form-control" required>${ticket.description}</textarea>
         </div>
     </div>
     <div class="form-group hide"><label class="col-xs-2 control-label">Attachments</label>	
   	    <div class="col-xs-10"><input  class="form-control" id="attachment1" type="file" name="..."/></div>
     </div>	
     <div class="form-group hide"><label class="col-xs-2 control-label"></label>	
   	    <div class="col-xs-10"><input  class="form-control" id="attachment2" type="file" name="..."/></div>
     </div>	
     <div class="form-group">
         <div class="col-xs-offset-8 col-xs-4">
               	<button id="submitBtn" class="btn btn-outline btn-success pull-right" type="button">Submit</button>
               	<span class="pull-right p-xxs"></span>
               	<button class="btn btn-outline btn-white pull-right" data-dismiss="modal">Cancel</button>
            </div>
     </div>
 </form>
           