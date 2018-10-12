<%@page import="za.co.juba.user.domain.User"%>
<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Notifications <small>Formal communication sent to all owners</small></h5>
                  <concept:permision role="${ sessionScope['concept.user'].role}">
	                  <div class="ibox-tools" >
	                       <button type="button" class="btn btn-primary pull-right btn-sm" onclick="notifications.displayCreate(); return false;">Create</button>
	                   </div>
                   </concept:permision>
              </div>
              <div class="ibox-content">
                  <div class="row">
                          <div class="col-sm-offset-9 col-sm-3">
                          <div class="input-group"><input id="searchInput" type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                          <button id="filterBtn" type="button" class="btn btn-sm"  onclick="units.clear(); return false;"> Clear</button> </span></div>
                      </div>
                  </div>
                  <div class="table-responsive">
                      <table class="table table-hover issue-tracker">
                          <tbody>
                          <c:forEach var="notification" items="${notifications}">
                          <tr>
                              <td class="hidden-xs">
                                  ${notification.sent }
                              </td>
                              <td class="hidden-xs">
                                  ${notification.author.name }
                              </td>
                              <td class="issue-info">
                                  <a href="#" onclick="notifications.view(${notification.id}); return false;">
                                      ${notification.subject }
                                  </a>
                              </td>
                              <td class="hidden-xs">
                                  ${notification.sent}
                              </td>
                              
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
 function Notifications(){}
 
 var notifications = new Notifications();
 
 Notifications.prototype.view = function(id){
	 nav.call('ViewNotification.nav', {intValue: id}, function(resp) {
		 var props = {};
			props.size = 'large';
			page.showModal(resp, function() {
				page.init();
			}, props);
	 });
 }
 
 Notifications.prototype.refresh = function(){
	 nav.call('Notifications.nav', {}, function(resp) {
		 $('#stage').html(resp);
	 });
 }
 
 Notifications.prototype.displayCreate = function(){
	 nav.call('CreateNotification.nav', {}, function(resp) {
			var props = {};
			props.size = 'large';
			props.closable = 'false';
			page.showModal(resp, function() {
				page.init();
			}, props);
		});
 }
  
 
 </script>
 
 
 