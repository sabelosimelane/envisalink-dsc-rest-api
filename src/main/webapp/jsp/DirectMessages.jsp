<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Direct Messages <small>Messages sent directly to owners</small></h5>
                  <div class="ibox-tools" >
                       <button type="button" class="btn btn-primary pull-right btn-sm" onclick="dm.displayCreate(); return false;">Create</button>
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
                      <table class="table table-hover issue-tracker">
                          <tbody>
                          <c:forEach var="message" items="${messages}">
                          <tr>
                              <td class="hidden-xs">
                                  ${message.sent.timestamp }
                              </td>
                              <td class="hidden-xs">
                                  ${message.unit.number}
                              </td>
                              <td class="hidden-xs">
                                  ${message.unit.owner.name}
                              </td>
                              <td class="issue-info">
                                  <a href="#" onclick="dm.view(${message.id}); return false;">
                                      ${message.subject }
                                  </a>
                              </td>
                              <td class="hidden-xs">
                                  ${message.reference}
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
 function DirectMessage(){}
 
 var dm = new DirectMessage();
 
 DirectMessage.prototype.view = function(id){
	 $('#uploadedFiles').remove();
	 nav.call('DirectMessageView.nav', {intValue: id}, function(resp){
		 $('#stage').html(resp);
	 });
 };
 
 DirectMessage.prototype.refresh = function(){
	 nav.call('DirectMessages.nav', {}, function(resp) {
		 $('#stage').html(resp);
	 });
 }
 
 DirectMessage.prototype.displayCreate = function(){
	 nav.call('CreateMessage.nav', {}, function(resp) {
			var props = {};
			props.size = 'large';
			props.closable = 'false';
			page.showModal(resp, function() {
				page.init();
			}, props);
		});
 }
 </script>
 
 
 