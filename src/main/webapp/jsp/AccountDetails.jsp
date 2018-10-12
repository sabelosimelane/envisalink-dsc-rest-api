<div class="ibox-title col-xs-offset-2 col-xs-8">
	<h5 id="formTitle" class="title">Account Settings <small>Update your account details to stay current</small></h5>
</div>
<div class="ibox-content col-xs-offset-2 col-xs-8">
     <form id="fileForm" class="hide">
	    <input id="fileinput" type="file" name="file" />
	</form>
    <form id="accountForm" class="form-horizontal">
        <div class="col-xs-12">
        	<div class="col-xs-offset-4 col-xs-4">
       				<img class="img-circle user-pic clickable" alt="" src="/pics/" 
       					onclick="$('#fileinput').click();" title="Upload your profile picture" style="width: 128px; height: 128px;"> 	
        	</div>
        </div>
        <div class="col-xs-12 p-xxs"></div>
        <div class="form-group">
        	<label class="col-xs-2 control-label" for="name">Name</label>
        	<div class="col-xs-9"><input id="name" name="name" type="text" placeholder="Your name" class="form-control" value="${user.name}" required maxlength="50"></div>
        </div>
        <div class="col-xs-12 p-xxs"></div>
        <div class="form-group">
        	<label class="col-xs-2 control-label" for="email">Email</label>
        	<div class="col-xs-9"><input  id="email" name="email" type="email" placeholder="Email" class="form-control" value="${user.email}" required maxlength="50"></div>
        </div>
        <div class="form-group">
        	<label class="col-xs-2 control-label" for="email">Cellphone</label>
        	<div class="col-xs-9"><input  id="cellphone" name="cellphone" type="number" placeholder="0820000000" class="form-control" value="${user.cellphone}" required maxlength="12"></div>
        </div>
         <div class="form-group">
        	<label class="col-xs-2 control-label" for="email">Unit</label>
        	<div class="col-xs-9"><input  class="form-control" value="${unit}" required maxlength="50" disabled></div>
        </div>
        <div class="col-xs-12 p-xxs"></div>
        <div class="form-group">
        	<label class="col-xs-2 control-label" for="password">Password</label>
        	<div class="col-xs-9"><input id="password" name="password" type="password" class="form-control" maxlength="50"></div>
        </div>
        <div class="col-xs-12 p-xxs"></div>
     		<div id="imgContainer"></div>
        
        <div class="form-group col-xs-12">
            <div class="col-xs-3 pull-right">
               <button id="submitAccount" type="button" class="btn btn-block btn-warning">Update my details</button>
            </div>
        </div>
    </form>
</div>

<script>
function AccountSettings(){}

var accountsettings = new AccountSettings();

var isJpg = function(name) {
    return name.match(/jpg$/i)
};
    
var isPng = function(name) {
    return name.match(/png$/i)
};

AccountSettings.prototype.submit = function(form){
	nav.submit('UpdateAccountDetails.nav', form, {}, function(resp){
		$('#logged-in-user-name').html($('#name').val());
		page.hideModal();
		message.show('Account details updated successfully.');	
	});
}
	
AccountSettings.prototype.bindUpload = function(){
	var file = $('[name="file"]');
    var imgContainer = $('#imgContainer');
    
    $('#fileinput').on('change', function() {
        var filename = $.trim(file.val());
        
        if (!(isJpg(filename) || isPng(filename))) {
            alert('Please browse a JPG/PNG file to upload ...');
            return;
        }
        
        var form = $('#fileForm')[0]; // You need to use standard javascript object here
        var formData = new FormData(form);
        
        $.ajax({
            url: '/Snippet/Upload',
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function(data, textStatus, jqXHR){
             	nav.call('SaveUserPicture.nav', {stringValue: data.FileName}, function(resp){
             		$('.user-pic').attr('src', '/pics/'+resp.filename+"?rand="+util.random(0, 100));
             		message.show('Profile pictures updated successfully.');
             	});
          	},
          	error : function(jqXHR, textStatus, errorThrown){
              //alert(jqXHR.responseText);
              alert('File upload failed ...');
          	}
          });
        
    });
    
    $('#btnClear').on('click', function() {
        imgContainer.html('');
        file.val('');
    });
}

$(function(){
		$("#submitAccount").click(function(){
			  $("#accountForm").submit();
		});
				
		$("#accountForm").validate({
			submitHandler: function(form) {
				accountsettings.submit(form);
			}
		});
	
		accountsettings.bindUpload();
		
		var picPath = $('.user-pic').attr('src');
		$('.user-pic').attr('src', picPath+'?rand='+util.random(0, 100));
});
	
	
	
</script>