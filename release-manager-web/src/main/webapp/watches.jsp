<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Watches</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">
        <h1>Watches</h1>
        <table class="table">
            <thead><tr><th>User</th><th>Subject</th></tr></thead>
            <tbody>
                <c:forEach var="watch" items="${watches}">
                <tr>
                    <td>${watch.user}</td>
                    <td>${watch.subject}</td>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/watches.html"
                            role="form" class="form-inline">
                            <input type="hidden" name="subject" value="${watch.subject}"/>
                            <input name="submit" value="Unwatch" class="btn btn-default btn-sm"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(watches)} watches(s)</p>
	</div>
</body>
</html>