<%@page import="za.co.juba.user.domain.User"%>
<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Discussion Board <small>Forum for owners to discuss topics started by Trustees</small></h5>
                  <concept:permision role="${ sessionScope['concept.user'].role}">
                  <div class="ibox-tools" >
                       <button type="button" class="btn btn-primary pull-right btn-sm" onclick="discussions.displayCreate(); return false;">Create</button>
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
                          <c:forEach var="discussion" items="${discussions}">
                          <tr class="comm" id="${discussion.id}">
                              <td class="hidden-xs">
                                  ${discussion.created.timestamp }
                              </td>
                              <td class="hidden-xs">
                                  ${discussion.author.name }
                              </td>
                              <td class="issue-info">
                                  <a href="#" onclick="discussions.view(${discussion.id}); return false;">
                                      ${discussion.subject }
                                  </a>
                              </td>
                              <td class="hidden-xs">
                              	 <span class="badge badge-primary">${fn:length(discussion.comments)}</span>
                              </td>
                              <td class="hidden-xs">
                                  ${discussion.public ? "Public" : "Private"}
                              </td>
                              <td class="hidden-xs">
                                  ${discussion.reference}
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
 <jsp:include page="UnseenComments.jsp"></jsp:include>
 
 <script>
 
 var commType = 2;
 
 function Discussion(){
 }
 
 var discussions = new Discussion();
 
 Discussion.prototype.create = function(form){
	  nav.call('SubmitNewDiscussion.nav', form, function(resp){
		  discussions.refresh();
		  page.hideModal();
	  });
 }
 
 Discussion.prototype.destroy = function(){
	  $('.summernote').summernote('destroy');
	  page.hideModal();
 }
 
 Discussion.prototype.view = function(id){
	 nav.call('DiscussionView.nav', {intValue: id}, function(resp){
		 $('#uploadedFiles').remove();
		 $('#stage').html(resp);
	 });
 };
 
 Discussion.prototype.refresh = function(){
	 nav.call('Discussions.nav', {}, function(resp) {
		 $('#stage').html(resp);
	 });
 }
 
 Discussion.prototype.displayCreate = function(){
	 nav.call('CreateDiscussion.nav', {}, function(resp) {
			var props = {};
			props.size = 'large';
			props.closable = 'false';
			page.showModal(resp, function() {
				page.init();
			}, props);
		});
 }
 
 </script>
 
 
 