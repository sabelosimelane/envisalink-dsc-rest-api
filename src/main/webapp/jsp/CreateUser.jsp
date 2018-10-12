<div class="col-xs-12">
      <div class="ibox float-e-margins">
          <div class="ibox-title">
              <h5>Create new user</h5>
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
				users.create($(form).serializeFormJSON(form));
			}
		});	
 	});
</script>