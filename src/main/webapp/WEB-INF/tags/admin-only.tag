<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="role" required="true"%>

<c:if test="${role == 1 }">
	<jsp:doBody></jsp:doBody>
</c:if>