<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-xs-12">
     <div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>Edit Discussion</h5>
         </div>
         <div class="ibox-content">
       		 <div class="mail-box">
                <div class="mail-body">
                    <form id="thisform" class="form-horizontal">
                        <input name="id" type="hidden" value="${discussion.id }"></input>
                        <div class="form-group"><label class="col-sm-2 control-label">Subject:</label>

                            <div class="col-sm-10"><input name="subject" type="text" class="form-control" required value="${discussion.subject }"></div>
                        </div>
                        <div class="form-group"><label class="col-sm-2 control-label">Public:</label>
							
							<div class="col-sm-10"><input name="isPublic" type="checkbox" class="js-switch" ${discussion.public ? "checked" :"" }/></div>
                        </div>
                        <concept:fileupload param1="DISCUSSION" fetchNavigator="FetchFilesForCommunication.nav" fetchParm="{'type': '2', 'communicationid': '${discussion.id}'}" deletenavigator="DeleteAttachment.nav"></concept:fileupload>
                        </form>
                </div>
                    <div class="mail-text h-200">
                        <div class="summernote"> ${discussion.description }</div>
							<div class="clearfix p-xxs"></div>
                        </div>
                    <div class="form-group">
				         <div class="col-xs-offset-8 col-xs-4">
				               	<button id="submitBtn" class="btn btn-outline btn-success pull-right" type="button">Submit</button>
				               	<span class="pull-right p-xxs"></span>
				               	<button class="btn btn-outline btn-white pull-right" onclick="discussions.destroy();">Cancel</button>
				            </div>
				     </div>
                    <div class="clearfix"></div>
                </div>
          </div>
     </div>
 </div>
  
  <script>

  Discussion.prototype.update = function(form){
	  nav.call('UpdateDicussion.nav', form, function(resp){
		  discussions.destroy();
		  discussions.refresh();
		  page.hideModal();
	  });
  }
  
  Discussion.prototype.destroy = function(){
	  $('.summernote').summernote('destroy');
	  page.hideModal();
  }
  
  $(function(){
	    $('.summernote').html(window.atob($('.summernote').html()));
	    $('.summernote').summernote();
  		
  		$("#submitBtn").click(function(){
  		  $("#thisform").submit();
	  	});
	  			
	  	$("#thisform").validate({
	  		submitHandler: function(form) {
	  			var thisform = $(form).serializeFormJSON(form); 
	  			thisform.description = window.btoa($('.summernote').summernote('code'));
	  			discussions.update(thisform);
	  		}
	  	});
  	});
  
  </script>	
		
		
		