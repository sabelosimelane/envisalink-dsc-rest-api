<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Units in complex <small>List of all units in the complex and their owners</small></h5>
                  <div class="ibox-tools" >
                       <button type="button" class="btn btn-primary pull-right btn-sm" disabled="disabled">Create</button>
                   </div>
              </div>
              <div class="ibox-content">
                  <div class="row">
                              <div class="col-sm-offset-9 col-sm-3">
                          <div class="input-group"><input id="searchInput" type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                          <button id="filterBtn" type="button" class="btn btn-sm"  onclick="units.clear(); return false;"> Clear</button> </span></div>
                      </div>
                  </div>
                  <div class="table-responsive">
                      <table class="table table-striped">
                          <thead>
                          <tr>

                              <th>#</th>
                              <th>Unit No. </th>
                              <th>Block </th>
                              <th>Owner </th>
                              <th>Levy Status </th>
                              <th>Action</th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach var="unit" items="${units}" varStatus="index">
                          <tr class="unit">
                              <td>${index.index+1 }</td>
                              <td class="unitnum">${unit.number}</td>
                              <td class="unitblock">${unit.block}</td>
                              <td class="owner">${unit.owner.surname} ${unit.owner.name}</td>
                              <td><i class="fa fa-check text-info"></i></td>
                              <td><a href="#" onclick="units.edit(${unit.id}); return false;"><i class="fa fa-edit"></i></a></td>
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

 function Units(){	 
	 $('#searchInput').keyup(function(){
		 units.filter($(this).val());
	});	
 }
 
 var units = new Units();
 
 Units.prototype.clear = function(param){
	 $('#searchInput').val('');
	 $('.table.table-striped tr').show();
 }
 
 Units.prototype.filter = function(param){
	
	 $('.table.table-striped tr').show();
	
	 $('.unit').each(function(){
		 if ($(this).find('.unitnum,.owner,.unitblock').text().trim().toLowerCase().indexOf(param.trim().toLowerCase()) === -1){
			 $(this).hide();
		 }
	 })

 }
 
 Units.prototype.update = function(unit){
	 nav.call('SubmitEditUnit.nav', unit, function(resp){
		 page.hideModal();
		 units.refresh();
		 message.show('Unit updated successfully.');
	 });
 }
 
 Units.prototype.edit = function(id){
	 nav.call('EditUnit.nav', {intValue: id}, function(resp){
		 var props = {};
			props.size = 'medium';
			page.showModal(resp, function() {
				page.init($('#unitform'));
		 }, props);
	 });
 }
 
 Units.prototype.refresh = function(){
	 nav.call('Units.nav', {}, function(resp){
		 $('#stage').html(resp);
	 });
 }
 
 </script>