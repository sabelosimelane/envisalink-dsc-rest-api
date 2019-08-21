<%@page import="com.grs.user.domain.User"%>
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
	                    ${message.subject }
	                </h2>
	                <hr>
	                
	                <div class="mail-tools tooltip-demo m-t-md col-sm-5">
	                    <h5>
	                        <span class="font-normal" >Recipient: </span> 
	                        <span class="pull-right">${message.recipient.name}</span>
	                    </h5>
	                    <h5>
	                        <span class="font-normal">Sent: </span>
	                        <span class="pull-right">${message.sent.timestamp }</span>
	                    </h5>
	                </div>
	                <div class="col-sm-2"></div>
	                <div class="mail-tools tooltip-demo m-t-md col-sm-5">
	                	<h5>
	                        <span class="font-normal" >Unit: </span> 
	                        <span class="pull-right">${message.unit.number}</span>
	                    </h5>
	                	<h5>
	                        <span class="font-normal" >Reference: </span> 
	                        <span class="pull-right">${message.reference}</span>
	                    </h5>
	                </div>
	                <h5>Attachments</h5>
					<concept:fileupload-view navigator="FetchFilesForCommunication.nav" parm="{'type': '3', 'communicationid': '${message.id }'}"></concept:fileupload-view>

            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
           			 <div class="pull-right tooltip-demo">
	                    <a href="#" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Back" onclick="dm.refresh(); return false;">
	                    <i class="fas fa-arrow-circle-left"></i> Back</a>
	                </div>
	                <div class="p-sm"></div>
	                <hr>
	                <div>
	                	<concept:permision role="4">
		                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="discussions.close(${message.id}); return false;">Close</button>
		                	<button type="button" class="btn btn-outline btn-primary btn-sm" onclick="discussions.load(${message.id}); return false;">Edit</button>
	                	</concept:permision>
	                </div>
	                
            </div>

					<div class="col-sm-8">
						<div class=" p-md">

							<div class="body mail-body">${message.body }</div>
							<div class="clearfix"></div>
						</div>
					</div>

				</div>
                 
                 
                 <div class="row">
				<div class="social-footer col-sm-8">
                            <c:forEach var="comment" items="${message.comments }">
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

<script>

DirectMessage.prototype.edit = function(){
	var body = $('.body.mail-body');
	var contents = window.atob($(body).html())
	$(body).html('<p>'+ contents.replace(/[\n\r]/g, '<br>') +'</p>');
}

DirectMessage.prototype.addComment = function(comment, callback){
	nav.call('AddComment.nav', comment, callback);
}

DirectMessage.prototype.load = function(id){
	nav.call('EditDiscussion.nav', {intValue: id}, function(resp){
		var props = {};
		props.size = 'large';
		props.closable = 'false';
		page.showModal(resp, function() {
			page.init();
		}, props);
	});
}

DirectMessage.prototype.hide = function(){
	$("[class*='quote'],blockquote").each(function(){
		$(this).hide();
	});
}

$(function(){
	dm.messageid = '${message.id}';
	$('#commenttextarea').focus();
	
	$('#commenttextarea').keypress(function (e) {
		util.enter(e, function(){
			if ($('#commenttextarea').val()){
				dm.addComment({id: dm.messageid, comment: $('#commenttextarea').val(), type: 3}, function(post){
					dm.view(dm.messageid);
				}); 
			}
		});
	});
	
	dm.hide();
	dm.edit();
});

</script>

