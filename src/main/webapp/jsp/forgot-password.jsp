<div class="ibox-title">
	<h5 id="formTitle" class="title">Password Reset</h5>
	<div class="ibox-tools">
		<a class="close-link" data-dismiss="modal"> <i class="fa fa-times"></i></a>
	</div>
</div>
<div class="ibox-content">
    <form id="passwordForm" class="form-horizontal">
        <div class="col-lg-12">
        	<div class="col-lg-offset-4 col-lg-4">
       				<img alt="" src="img/logo.png" width="100%"> 	
        	</div>
        </div>
        <div class="col-lg-12 p-xxs"></div>
        <div class="col-lg-12">
        	<p>Enter your email address below and we'll send you password reset instructions.</p>
        </div>
        <div class="col-lg-12 p-xxs"></div>
        <div class="form-group"><label class="col-lg-2 control-label">Email</label>
        <div class="col-lg-9"><input id="email-forgot" type="email" placeholder="Email" class="form-control" required></div>
        </div>
        <div class="col-lg-12 p-xxs"></div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-9">
               <button id="send" type="button" class="btn btn-block btn-outline btn-primary">Send me reset instructions</button>
            </div>
        </div>
    </form>
</div>
<script>

$(function(){
	$("#send").click(function(){
		  $("#passwordForm").submit();
	});
			
	$("#passwordForm").validate({
		submitHandler: function(form) {
			nav.call('ResetPassword.nav', {stringValue: $('#email-forgot').val()}, function(resp){
				page.hideModal();
	            swal({
	                title: "Check your email",
	                text: "If you are registered with us, please follow instructions on the email.",
	                type: "success"
	            });
			}, function(resp){
				
			});
		}
	});

});

</script>