<%@ taglib prefix="concept" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="param1" required="false"%>
<%@ attribute name="param2" required="false"%>
<%@ attribute name="fetchNavigator"%>
<%@ attribute name="fetchParm"%>
<%@ attribute name="deletenavigator"%>

<div class="form-group">
	<label class="col-xs-2 control-label">Attachments</label>
	<div class="col-xs-10">
		<input class="form-control" id="attachment1" type="file" name="..."
			param1="${param1}" param2="${param2}" />
	</div>
	<div class="upload-progress col-xs-offset-2 col-xs-10"
		style="display: none;">
		<div class="progress progress-mini">
			<div class="progress-bar" style="width: 0%;"></div>
		</div>
	</div>
	<div class="col-xs-12 p-xxs"></div>
	<div class="col-xs-offset-2 col-xs-10">
		<div id="uploadedFiles">
			<div class="clone hide">
				<a class="filepath" target="_black" href="#">
				<i class="fas fa-file"></i>
				<span class="fileinput-filename">temp file</span></a> <a
					href="#" class="close fileinput-exists" data-dismiss="fileinput"
					style="float: none">×</a>
			</div>
		</div>
	</div>

</div>

<script>

FileUpload.prototype.upload = function(input, params){
	$('#attachment1').attr('disabled', 'disabled');
	$(".upload-progress").fadeIn('normal');
	var file = $(input)[0].files[0];
    var upload = new FileUpload(app.root +  'Upload',file);
    
    // maby check size or type here with upload.getSize() and upload.getType()

    // execute upload
    upload.doUpload(function(){
    	nav.call('FetchUploadedFiles.nav', {}, function(resp){
    		$('#uploadedFiles .cloned:not([fileid])').remove();
    		util.displayCollection($('#uploadedFiles'), resp.files, function(item, clone){
				$(clone).find('.fileinput-filename').html(item.displayName);
			});
    		$('#attachment1').val("");
    		$(".upload-progress").fadeOut('normal', function(){
    			$('div.progress-bar').css('width', '0%');
    			$('#attachment1').removeAttr('disabled');
    		});
    		
    	});
    	
    }, function(){
    	alert('error occured');
    }, function(progress){
    	console.log('progress: ' + progress+'%');
    	$('div.progress-bar').css('width', progress+'%');
    }, params);
} 

function load(){
	
	nav.call('${fetchNavigator}', <%= fetchParm==null?"{}":fetchParm %>, function(resp){
		util.displayCollection($('#uploadedFiles'), resp, function(item, clone){
			$(clone).find('.fileinput-filename').html(item.displayName);
			$(clone).find('.filepath').attr('href', item.filename);
			$(clone).attr('fileid', item.id);
		});
		
		if ('${deletenavigator}'){
			$('#uploadedFiles .fileinput-exists').click(function(){
				fileupload.delete('${deletenavigator}', $(this).closest('.cloned').attr('fileid'));
			});
		} else {
			$('#uploadedFiles a.close').hide();
		}
	});
}

$(function(){
		nav.call('StartUpload.nav', {}, function(resp){
		});
		
		$('#attachment1').change(function(){
			fileupload.upload($('#attachment1'), {'param1': '${param1}', 'param2': '${param2}'});	
		});
		
		if ('${fetchNavigator}'){
			load();
		}
	});

</script>