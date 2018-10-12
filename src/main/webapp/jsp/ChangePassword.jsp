<div class="row">
    <form id="passwordForm" class="form-horizontal" role="form" data-toggle="validator">
        <div class="col-lg-12 p-xxs"></div>
        <div class="col-lg-12">
        	<p>Enter your email address below and we'll send you password reset instructions.</p>
        </div>
        <input type="hidden" name="reference" value="<%=request.getAttribute("reference")%>">
        <div class="col-lg-12 p-xxs"></div>
        <div class="form-group"><label class="col-lg-3 control-label">Email</label>
        	<div class="col-lg-8"><input name="email" type="email" placeholder="Email" class="form-control" required maxlength="60" data-minlength="4"></div>
        </div>

		<div class="col-lg-12 p-xxs"></div>
        <div class="form-group"><label class="col-lg-3 control-label">Password</label>
        	<div class="col-lg-8"><input id="password" name="password" type="password" class="form-control" required maxlength="60" data-minlength="6"></div>
        </div>

        <div class="col-lg-12 p-xxs"></div>
        <div class="form-group">
            <div class="col-lg-offset-3 col-lg-8">
               <button id="send" type="button" class="btn btn-block btn-outline btn-primary">Change my password</button>
            </div>
        </div>
    </form>
</div>
<script>
	$(function(){
		$('#pageTitle').html("Let's change your password!");
		
		
		$("#send").click(function(){
			  $("#passwordForm").submit();
		});
				
		$("#passwordForm").validate({
			submitHandler: function(form) {
	        	nav.submit('SubmitPasswordChange.nav', form, {}, function(resp){
	        		swal({
		                title: "Done!",
		                text: "Your password has been changed successfully.",
		                type: "success",
		                closeOnConfirm: false
		            }, function () {
		                window.location = "/Pollux";
		            });	
	        	});
			}
		});

	});
</script>