<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">

      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title" style="padding-bottom: 40px !important;">
                  <h5>Create Tokens <small>List of all users enrolled to use the portal</small></h5>
              </div>
              <div class="ibox-content">
                  <div class="row">
                       <div class="col-sm-2">
                          <button type="button" class="btn btn-sm" onclick="admin.createToken(); return false;"> Create Tokens</button>
                         </div>
                  </div>
                  

              </div>
          </div>
      </div>
 </div>
 
 <script>

 function Admin(){	 
 }
 
 var admin = new Admin();
 
 Admin.prototype.createToken = function(){
	 nav.call('CreateAccessToken.nav', {}, function(resp){
			
		});	 
 }
 
 
 	$(function(){
 		
 		
 	});
 
 </script>