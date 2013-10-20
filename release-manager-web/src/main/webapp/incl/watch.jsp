<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" class="form-inline" method="post" action="${pageContext.request.contextPath}/watches.html"
             style="display:inline">
    <input type="hidden" name="subject" value="page:<%=request.getAttribute("javax.servlet.forward.request_uri").toString().substring(request.getContextPath().length())%>"/>
    <c:choose>
    <c:when test="${not watching}">
        <button type="submit" class="btn btn-default btn-sm" name="submit" value="Watch">Watch</button>
    </c:when>
    <c:otherwise>
        <button type="submit" class="btn btn-default btn-sm" name="submit" value="Unwatch">Unwatch</button>
    </c:otherwise>
    </c:choose>
</form>