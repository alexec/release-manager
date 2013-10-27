<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Components</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">
        <h1>Audit Logs</h1>
        <table class="table">
            <thead><tr><th>Created</th><th>Username</th><th>Messages</th></tr></thead>
            <tbody>
                <c:forEach var="audit_log" items="${audit_logs}">
                <tr>
                    <td>${audit_log.created}</td>
                    <td>${audit_log.username}</td>
                    <td>${audit_log.message}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(audit_logs)} audit log(s)</p>
	</div>
</body>
</html>