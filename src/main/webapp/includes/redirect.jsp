<%@page import="java.util.Enumeration"%>
<%@ page import = "java.util.Map" %>

<form id="redirectForm">
<%
Enumeration en = request.getParameterNames();
while (en.hasMoreElements()) {
	String parameterName = (String) en.nextElement();
	String parameterValue = request.getParameter(parameterName);
%>		<input type="hidden" id="<%=parameterName %>" name="<%=parameterName %>" value="<%=parameterValue%>">        

<%   
}
 %>
</form>

<script>
var redirect = new Redirect();
$(function(){
	if ($("#redirectForm").length > 0){
   		redirect.redirect();
   		return;
   }
})

</script>