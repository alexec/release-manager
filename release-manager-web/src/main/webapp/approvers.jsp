<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Approvers</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">
        <h1>Approvers</h1>
        <table class="table">
            <thead><tr><td>Name</td></tr></thead>
            <tbody>
                <c:forEach var="approver" items="${approvers}">
                <tr>
                    <td>${approver.name}</td>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/approvers/${approver.id}.html"
                            role="form" class="form-inline">
                            <input class="btn btn-default btn-sm" value="Remove"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(approvers)} approver(s)</p>
            <p>
                <a href="${pageContext.request.contextPath}/approvers/create.html">Create</a>
        </p>
	</div>
</body>
</html>