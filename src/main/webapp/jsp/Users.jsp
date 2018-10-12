<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Users <small>List of all users enrolled to use the portal</small></h5>
                   <div class="ibox-tools" >
                       <a href="#" class="btn btn-primary pull-right btn-sm" onclick="users.displayCreate(); return false;">Create</a>
                   </div>
              </div>
              <div class="ibox-content">
                  <div class="row">
                              <div class="col-sm-offset-9 col-sm-3">
                          <div class="input-group"><input type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                          <button type="button" class="btn btn-sm"> Go!</button> </span></div>
                      </div>
                  </div>
                  <div class="table-responsive">
                      <table class="table table-striped">
                          <thead>
                          <tr>

                              <th>#</th>
                              <th>Name </th>
                              <th>Email </th>
                              <th>Cellphone </th>
                              <th>Unit</th>
                              <th>Active </th>
                              <th>Role</th>
                              <th>Last login</th>
                              <th>Action</th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach var="user" items="${users}" varStatus="index">
                          <tr>
                              <td>${index.index+1 }</td>
                              <td>${user.name}</td>
                              <td>${user.email}</td>
                              <td>${user.cellphone}</td>
                              <td>${user.unit}</td>
                              <td>${user.active}</td>
                              <td>${user.role.name}</td>
                              <td>${user.lastlogin.prettyshort}</td>
                              <td><a href="#" onclick="users.edit(${user.id}); return false;"><i class="fas fa-user-edit"></i></a></td>
                          </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>

              </div>
          </div>
      </div>
 </div>
 
 <script>
 function Users(){}

Users.prototype.displayCreate = function(){
	 nav.loadJSP('jsp/CreateUser.jsp', function(resp) {
		var props = {};
		props.size = 'medium';
		page.showModal(resp, function() {
			page.init();
		}, props);
	});
}
 
 Users.prototype.update = function(user){
	 nav.call('SubmitEditUser.nav', user, function(resp){
		 page.hideModal();
		 users.refresh();
		 message.show('User updated successfully.');
	 });
 }
 
 Users.prototype.edit = function(userid){
	 nav.call('EditUser.nav', {intValue: userid}, function(resp){
		 var props = {};
			props.size = 'medium';
			page.showModal(resp, function() {
				page.init($('#userform'));
		 }, props);
	 });
 }
 
 Users.prototype.create = function(user){
	 nav.call('CreateUser.nav', user, function(resp){
		 page.hideModal();
		 users.refresh();
		 message.show('User created successfully.');
	 });
 }
 
 Users.prototype.refresh = function(){
	 nav.call('Users.nav', {}, function(resp){
		 $('#stage').html(resp);
	 });
 }
  
 var users = new Users();
 
 </script>