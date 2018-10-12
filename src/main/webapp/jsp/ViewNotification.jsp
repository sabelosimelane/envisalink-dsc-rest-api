<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>

<div class="col-xs-12">
     <div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>Notification</h5>
             
             <a class="close-link pull-right">
                   <i class="fa fa-times" onclick="page.hideModal();"></i>
               </a>
         </div>
         <div class="ibox-content">


			<div class="mail-box-header">
				<h2>${notification.subject }</h2>
				<div class="mail-tools tooltip-demo m-t-md">
					<h5>
						<span class="font-normal">From: </span>${notification.author.name }
					</h5>
					<h5>
						<span class="font-normal">Sent: </span>${notification.sent }
					</h5>
					
					<hr>
					<h5>Attachments</h5>
					<concept:fileupload-view navigator="FetchFilesForCommunication.nav" parm="{'type': '4', 'communicationid': '${notification.id }'}" deletenavigator="DeleteAttachment.nav"></concept:fileupload-view>
				</div>
			</div>
			<div class="mail-box ">
				<div class="mail-body"></div>

			</div>

		</div>
     </div>
</div>   
<script>
    
  notifications.body = '${notification.body}';
  $(function(){
	 $('.mail-body').html(window.atob(notifications.body));
  });
     
 </script>
 
 
 