<%@ taglib prefix="concept" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="parm" required="true"%>
<%@ attribute name="navigator" required="true"%>
<%@ attribute name="deletenavigator"%>

<div id="uploadedFiles">
	<div class="file-item clone hide">
		<a class="filepath" target="_black" href="#">
		<i class="fas fa-file"></i>
		<span class="fileinput-filename">temp file</span></a> 
			<a href="#" class="close fileinput-exists" data-dismiss="fileinput" style="float: none">×</a>
	</div>
</div>

<script>

function load(){
	
	nav.call('${navigator}', ${parm}, function(resp){
		util.displayCollection($('#uploadedFiles'), resp, function(item, clone){
			$(clone).find('.fileinput-filename').html(item.displayName);
			$(clone).find('.filepath').attr('href', item.filename);
			$(clone).attr('fileid', item.id);
		});
		
		if ('${deletenavigator}'){
			$('#uploadedFiles .fileinput-exists').click(function(){
				fileupload.delete('${deletenavigator}', $(this).closest('.file-item').attr('fileid'));
			});
		} else {
			$('#uploadedFiles a.close').hide();
		}
	});
}

$(function(){
	load();
});

</script>