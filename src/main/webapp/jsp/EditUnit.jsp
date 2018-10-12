<div class="col-xs-12">
      <div class="ibox float-e-margins">
          <div class="ibox-title">
              <h5>Update unit</h5>
          </div>
          <div class="ibox-content">
          		<jsp:include page="unit/unitform.jsp"></jsp:include>            
           </div>
      </div>
  </div>
  
<script>
 
 	$(function(){
		$("#submitBtn").click(function(){
		  $("#unitform").submit();
		});
			
		$("#unitform").validate({
			submitHandler: function(form) {
				units.update($(form).serializeFormJSON(form));
			}
		});	
 	});
</script>