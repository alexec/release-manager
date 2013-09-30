<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    Map nav = new LinkedHashMap();
    String cp = request.getContextPath();
    nav.put(cp + "/index.html", "Home");
    nav.put(cp + "/releases.html", "Releases");
    nav.put(cp + "/components.html", "Components");
    pageContext.setAttribute("nav", nav);
%>
<ul class="nav nav-pills">
    <c:forEach items="${nav}" var="item">
    <li class="${pageContext.request.uri.path == item.key ? 'active' : ''}"><a href="${item.key}">${item.value}</a></li>
    </c:forEach>
</ul>
