<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 <form id="unitform" class="form-horizontal">
     <input name="id" type="hidden" value="${unit.id }"></input>
     <div class="form-group"><label class="col-xs-2 control-label">Number</label>
         <div class="col-xs-10"><input type="text" class="form-control" value="${unit.number}" disabled="disabled">
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Block</label>
         <div class="col-xs-10"><input type="text" class="form-control" disabled="disabled" value="${unit.block}">
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Owner</label>
         <div class="col-xs-10">
         	<select name="ownerid" data-placeholder="Choose Owner" class="chosen-select form-control" required value="${unit.owner.id }">
				<option value="1">Select Owner</option>
				<c:forEach var="user" items="${users}">
					<option value="${user.id}">${user.surname} ${user.name}</option>
				</c:forEach>
			</select>
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
           