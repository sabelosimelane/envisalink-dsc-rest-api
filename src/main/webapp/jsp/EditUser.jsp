<div class="col-xs-12">
      <div class="ibox float-e-margins">
          <div class="ibox-title">
              <h5>Update user</h5>
          </div>
          <div class="ibox-content">
          		<jsp:include page="user/userform.jsp"></jsp:include>            
           </div>
      </div>
  </div>
  
<script>
 
 	$(function(){
		$("#submitBtn").click(function(){
		  $("#userform").submit();
		});
			
		$("#userform").validate({
			submitHandler: function(form) {
				users.update($(form).serializeFormJSON(form));
			}
		});	
 	});
</script>