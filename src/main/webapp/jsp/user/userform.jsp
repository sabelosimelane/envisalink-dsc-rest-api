 <form id="userform" class="form-horizontal">
     <input name="id" type="hidden" value="${user.id }"></input>
     <div class="form-group"><label class="col-xs-2 control-label">Name</label>
         <div class="col-xs-10"><input name="name" type="text" placeholder="e.g. Ron" class="form-control" required value="${user.name }">
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Email</label>
         <div class="col-xs-10"><input name="email" type="email" placeholder="Email" class="form-control" required value="${user.email }">
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Cellphone</label>
         <div class="col-xs-10"><input name="cellphone" type="number" placeholder="e.g. 0821234567" class="form-control" value="${user.cellphone }">
         </div>
     </div>
     <div class="form-group"><label class="col-xs-2 control-label">Role</label>
         <div class="col-xs-10">
         	<select name="role" data-placeholder="Choose Role" class="chosen-select form-control" required value="${user.role.id }">
				<option value="3">Owner</option>
				<option value="2">Trustee</option>
				<option value="5">Managing Agent</option>
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
           