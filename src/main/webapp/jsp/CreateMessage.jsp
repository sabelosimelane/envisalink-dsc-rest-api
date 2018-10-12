<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-xs-12">
     <div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>Send Message Directly to Owner</h5>
         </div>
         <div class="ibox-content">
       		 <div class="mail-box">
                <div class="mail-body">
                    <form id="thisform" class="form-horizontal">
                        <div class="form-group"><label class="col-sm-2 control-label">Unit:</label>
                           <div class="col-sm-10">
                            <select name="unitid" data-placeholder="Choose Assignee" class="chosen-select form-control" required>
								<c:forEach var="unit" items="${units }">
									<option value="${unit.id}">${unit.block}</option>
								</c:forEach>
							</select>
							</div>
                        </div>
                        <div class="form-group"><label class="col-sm-2 control-label">Subject:</label>
                            <div class="col-sm-10"><input name="subject" type="text" class="form-control" required></div>
                        </div>
                        <concept:fileupload param1="DIRECT_MESSAGE" fetchNavigator="FetchFilesForCommunication.nav" fetchParm="{'type': '3', 'communicationid': '${discussion.id}'}" deletenavigator="DeleteAttachment.nav"></concept:fileupload>
                        </form>
                </div>
                    <div class="mail-text h-200">
                        <div class="summernote">
                        
                        Regards [Author]
                        </div>
							<div class="clearfix p-xxs"></div>
                        </div>
                    <div class="form-group">
				         <div class="col-xs-offset-8 col-xs-4">
				               	<button id="submitBtn" class="btn btn-outline btn-success pull-right" type="button">Send</button>
				               	<span class="pull-right p-xxs"></span>
				               	<button class="btn btn-outline btn-white pull-right" onclick="dm.destroy();">Discard</button>
				            </div>
				     </div>
                    <div class="clearfix"></div>
                </div>
          </div>
     </div>
 </div>
  
  <script>

  DirectMessage.prototype.send = function(form){
	  nav.call('SendDirectMessage.nav', form, function(resp){
		  dm.refresh();
		  dm.destroy();
	  });
  }
  
  DirectMessage.prototype.destroy = function(){
	  $('.summernote').summernote('destroy');
	  page.hideModal();
  }
  
  $(function(){
  		$('.summernote').summernote();
  		
  		$("#submitBtn").click(function(){
  		  $("#thisform").submit();
	  	});
	  			
	  	$("#thisform").validate({
	  		submitHandler: function(form) {
	  			var thisform = $(form).serializeFormJSON(form); 
	  			thisform.body = window.btoa($('.summernote').summernote('code'));
	  			dm.send(thisform);
	  		}
	  	});
  	});
  
  </script>	
		
		
		