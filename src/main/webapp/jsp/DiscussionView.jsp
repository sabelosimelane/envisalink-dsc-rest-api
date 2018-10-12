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
	                    ${discussion.subject }
	                </h2>
	                <hr>
	                
	                <div class="mail-tools tooltip-demo m-t-md col-sm-5">
	                    <h5>
	                        <span class="font-normal" >Reference: </span> 
	                        <span class="pull-right">${discussion.reference}</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal">Created: </span>
	                        <span class="pull-right">${discussion.created.timestamp }</span>
	                    </h5>
	                </div>
	                <div class="col-sm-2"></div>
	        
            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
           			 <div class="pull-right tooltip-demo">
	                    <a href="#" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Back" onclick="discussions.refresh(); return false;">
	                    <i class="fas fa-arrow-circle-left"></i> Back</a>
	                </div>
	                <div class="p-sm"></div>
	                <hr>
	                <div>
	                	<concept:permision role="${ sessionScope['concept.user'].role}">
		                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="discussions.close(${discussion.id}); return false;">Close</button>
		                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="discussions.load(${discussion.id}); return false;">Edit</button>
	                	</concept:permision>
	                </div>
	                
            </div>
        	<div class="col-sm-8">
				<h5>Attachments</h5>
				<concept:fileupload-view navigator="FetchFilesForCommunication.nav" parm="{'type': '2', 'communicationid': '${discussion.id}'}"></concept:fileupload-view>
				
			</div>			
					<div class="col-sm-8">
						<div class=" p-md">

							<div class="body mail-body">${discussion.description }</div>
							<div class="clearfix"></div>
						</div>
					</div>

				</div>
                 
                 
                 <div class="row">
				<div class="social-footer col-sm-8">
                            <c:forEach var="comment" items="${discussion.comments }">
                            <div class="social-comment">
                                <a href="" class="pull-left">
                                    <img alt="image" class="img-circle" src="img/user.png">
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
                                    <img alt="image" class="img-circle" src="img/user.png">
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

var commType = 2;

Discussion.prototype.close = function(id){
	swal({
        title: "Please confirm",
        text: "Are you sure you want to close this discussion?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, do it!",
        closeOnConfirm: true
    }, function () {
    	nav.call('CloseDiscussion.nav', {intValue: id}, function(resp){
    		discussions.refresh();
    	});
    });
}

Discussion.prototype.resolve = function(ticketid){
	nav.call('ResolveTicket.nav', {intValue: ticketid}, function(resp){
		tickets.refresh();
	});
}

Discussion.prototype.edit = function(){
	var body = $('.body.mail-body');
	var contents = window.atob($(body).html())
	$(body).html('<p>'+ contents.replace(/[\n\r]/g, '<br>') +'</p>');
}

Discussion.prototype.addComment = function(comment, callback){
	nav.call('AddComment.nav', comment, callback);
}

Discussion.prototype.load = function(id){
	nav.call('EditDiscussion.nav', {intValue: id}, function(resp){
		var props = {};
		props.size = 'large';
		props.closable = 'false';
		page.showModal(resp, function() {
			page.init();
		}, props);
	});
}

Discussion.prototype.hide = function(){
	$("[class*='quote'],blockquote").each(function(){
		$(this).hide();
	});
}

$(function(){
	discussions.edit();
	discussions.discussionid = '${discussion.id}';
	$('#commenttextarea').focus();
	
	$('#commenttextarea').keypress(function (e) {
		util.enter(e, function(){
			if ($('#commenttextarea').val()){
				discussions.addComment({id: discussions.discussionid, comment: $('#commenttextarea').val(), type: 2}, function(post){
					discussions.view(discussions.discussionid);
				}); 
			}
		});
	});
	
	discussions.hide();
	
	comments.recordSeen('${discussion.id}', commType); 
});

</script>

