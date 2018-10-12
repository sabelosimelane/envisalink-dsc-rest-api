<%@page import="za.co.juba.user.domain.User"%>
<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-sm-12">
          <div class="ibox float-e-margins">

              <div class="ibox-content">
                  <div class="row">
                  	
                <div class="col-sm-8 animated fadeInRight">
	                
	                <h2>
	                    ${ticket.subject }
	                </h2>
	                <hr>
	                
	                <div class="mail-tools tooltip-demo m-t-md col-sm-5">
	                    <h5>
	                        <span class="font-normal" >Reference: </span> 
	                        <span class="pull-right">${ticket.reference}</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal" >Assignee: </span> 
	                        <span class="pull-right">${ticket.assignee.name }</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal">Created: </span>
	                        <span class="pull-right">${ticket.created }</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal">Reporter: </span>
	                        <span class="pull-right">${ticket.reporter.name }</span>
	                    </h5>
	                    
	                    <h5>Attachments</h5>
						<concept:fileupload-view navigator="FetchFilesForCommunication.nav" parm="{'type': '1', 'communicationid': '${ticket.id}'}"></concept:fileupload-view>
	                </div>
	              	
	                <div class="col-sm-2"></div>
	                <div class="mail-tools tooltip-demo m-t-md col-sm-4">
	            		<h5>
	                        <span class="font-normal" >Status: </span> 
	                        <span class="pull-right">${ticket.status.description}</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal" >Unit: </span> 
	                        <span class="pull-right">${ticket.unit == null ? 'N/A' : ticket.unit.id}</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal" >Type: </span> <span class="label label-warning pull-right">${ticket.type.description }</span>
	                    </h5>    
	                </div>

            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
           			 <div class="pull-right tooltip-demo">
	                    <a href="#" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Back" onclick="tickets.refresh(); return false;">
	                    <i class="fas fa-arrow-circle-left"></i> Back</a>
	                </div>
	                <div class="p-sm"></div>
	                <hr>
	                <div>
	                	<concept:permision role="${ sessionScope['concept.user'].role}">
	                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="tickets.editAssign(${ticket.id}); return false;">Assign</button>
	                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="tickets.resolve(${ticket.id}); return false;">Resolve</button>
	                	</concept:permision>
	                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="tickets.resolve(${ticket.id}); return false;">Close</button>
	                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="tickets.load(${ticket.id}); return false;">Edit</button>
	                </div>
	                
            </div>
            
            <div class="col-sm-8">
                <div class=" p-md">
				
					<div class="body mail-body">
                    	${ticket.description }
                	</div>
				
				    <div class="mail-attachment hide">
                        <p>
                            <span><i class="fa fa-paperclip"></i> 2 attachments - </span>
                            <a href="#">Download all</a>
                            |
                            <a href="#">View all images</a>
                        </p>

                        <div class="attachment">
                            <div class="file-box">
                                <div class="file">
                                    <a href="#">
                                        <span class="corner"></span>

                                        <div class="icon">
                                            <i class="fa fa-file"></i>
                                        </div>
                                        <div class="file-name">
                                            Document_2014.doc
                                            <br/>
                                            <small>Added: Jan 11, 2014</small>
                                        </div>
                                    </a>
                                </div>

                            </div>
                            <div class="file-box">
                                <div class="file">
                                    <a href="#">
                                        <span class="corner"></span>

                                        <div class="image">
                                            <img alt="image" class="img-responsive" src="img/p1.jpg">
                                        </div>
                                        <div class="file-name">
                                            Italy street.jpg
                                            <br/>
                                            <small>Added: Jan 6, 2014</small>
                                        </div>
                                    </a>

                                </div>
                            </div>
                            <div class="file-box">
                                <div class="file">
                                    <a href="#">
                                        <span class="corner"></span>

                                        <div class="image">
                                            <img alt="image" class="img-responsive" src="img/p2.jpg">
                                        </div>
                                        <div class="file-name">
                                            My feel.png
                                            <br/>
                                            <small>Added: Jan 7, 2014</small>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        </div>
                        <div class="clearfix"></div>
                </div>
            </div>
                  	
                 </div>
                 
                 
                 <div class="row">
				<div class="social-footer col-sm-8">
                            <c:forEach var="comment" items="${ticket.comments }">
                            <div class="social-comment">
                                <a href="" class="pull-left">
                                    <img alt="image" class="img-circle" src="img/a3.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="#">
                                       ${comment.user.name }
                                    </a>
                                     ${comment.comment }
                                    <br/>
                                    <!-- <a href="#" class="small"><i class="fa fa-thumbs-up"></i> 26 Like this!</a> --> -
                                    <small class="text-muted">${comment.timestamp.prettyshort }</small>
                                </div>
                            </div>
							</c:forEach>
                            <div class="social-comment">
                                <a href="" class="pull-left">
                                    <img alt="image" class="img-circle" src="img/a3.jpg">
                                </a>
                                <div class="media-body">
                                     <textarea id="commenttextarea" class="form-control" placeholder="Write comment..."></textarea>
                                </div>
                            </div>

                        </div>
					</div>
                 
              </div>
            </div>
        </div>
</div>        

<jsp:include page="UnseenComments.jsp"></jsp:include>
<script>

var commType = 1;

Tickets.prototype.resolve = function(ticketid){
	swal({
        title: "Please confirm",
        text: "Are you sure you want to resolve this ticket?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, do it!",
        closeOnConfirm: true
    }, function () {
    	nav.call('ResolveTicket.nav', {intValue: ticketid}, function(resp){
    		tickets.refresh();
    	});
    });
}

Tickets.prototype.edit = function(){
	var body = $('.body.mail-body');
	var contents = $(body).html();
	$(body).html('<p>'+ contents.replace(/[\n\r]/g, '<br>') +'</p>');
}

Tickets.prototype.addComment = function(comment, callback){
	nav.call('AddComment.nav', comment, callback);
}

Tickets.prototype.editAssign = function(ticketid){
	nav.call('AssignTicket.nav', {intValue: ticketid}, function(resp){
		var props = {};
		props.size = 'medium';
		page.showModal(resp, function() {
			page.init();
		}, props);
	});
}

Tickets.prototype.hide = function(){
	$("[class*='quote'],blockquote").each(function(){
		$(this).hide();
	});
}

$(function(){
	tickets.edit();
	tickets.ticketid = '${ticket.id}';
	$('#commenttextarea').focus();
	
	$('#commenttextarea').keypress(function (e) {
		util.enter(e, function(){
			if ($('#commenttextarea').val()){
				tickets.addComment({id: tickets.ticketid, comment: $('#commenttextarea').val(), type: 1}, function(post){
					tickets.view(tickets.ticketid);
				});
			}
		});
	});
	
	tickets.hide();
	
	comments.recordSeen('${ticket.id}', commType); 
});

</script>

