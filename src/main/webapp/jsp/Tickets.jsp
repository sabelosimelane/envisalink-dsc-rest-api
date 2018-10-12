<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Tickets <small>Issues raised by owners allocated to trustees, caretaker or managing agent</small></h5>
                  <div class="ibox-tools" >
                       <button type="button" class="btn btn-primary pull-right btn-sm" onclick="tickets.displayCreate(); return false;">Create</button>
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
                          <c:forEach var="ticket" items="${tickets}">
                          <tr class="comm" id="${ticket.id}">
                              <td class="hidden-xs">
                                  ${ticket.created }
                              </td>
                              <td class="hidden-xs">
                                  <span class="label">${ticket.type }</span>
                              </td>
                              <td class="issue-info">
                                  <a href="#" onclick="tickets.view(${ticket.id}); return false;">
                                      ${ticket.subject }
                                  </a>

                                  <small class="ticket-body">
                                      ${ticket.description }
                                  </small>
                              </td>
                              <td class="hidden-xs">
                              	 <span class="badge badge-primary">${fn:length(ticket.comments)}</span>
                              </td>
                              <td class="hidden-xs">
                                  ${ticket.reference }
                              </td>
                              <td class="hidden-xs">
                                  ${ticket.reporter.name }
                              </td>
                              <td class="hidden-xs">
                                  <span class="label label-warning">${ticket.status.description }</span>
                              </td>
                              <td><a href="#" onclick="tickets.load(${ticket.id}); return false;"><i class="fas fa-edit"></i></a> </td>
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
 var commType = 1;
 
 function Tickets(){
	$(".ticket-body").each(function(){
		var content = $(this).text();
		if (content.length > 127) {
			$(this).html(content.substring(0, 130)+'...');
		}
	});
 }
 
 var tickets = new Tickets();
 
 Tickets.prototype.view = function(id){
	 $('#uploadedFiles').remove();
	 nav.call('TicketView.nav', {intValue: id}, function(resp){
		 $('#stage').html(resp);
	 });
 };
 
 Tickets.prototype.load = function(id){
 	nav.call('EditTicket.nav', {intValue: id}, function(resp){
 		var props = {};
		props.size = 'large';
		page.showModal(resp, function() {
			page.init();
		}, props);
 	});
 }
	 
 Tickets.prototype.refresh = function(){
	 nav.call('Tickets.nav', {}, function(resp){
		 $('#stage').html(resp);
	 });
 }
 
 Tickets.prototype.create = function(ticket){
	 nav.call('SubmitNewTicket.nav', ticket, function(resp){
		 tickets.refresh();
		 message.show('Ticket created successfully.');
		 page.hideModal();
	 });
 };
  
 Tickets.prototype.displayCreate = function(){
	 nav.call('CreateTicket.nav', {}, function(resp) {
		var props = {};
		props.size = 'large';
		page.showModal(resp, function() {
			page.init();
		}, props);
	});
}
 
 </script>
 
 
 