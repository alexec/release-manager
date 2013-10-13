<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Releases</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="/incl/nav.jsp"/>
        <h1>Releases</h1>
        <table class="table">
            <thead><tr><th>Name</th><td>Status</th><th></th></tr></thead>
            <tbody>
                <c:forEach var="release" items="${releases}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/releases/${release.id}.html">${release.name}</a>
                    </td>
                    <td>
                        ${release.status}
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/releases/${release.id}.html?edit=true">Edit<a>
                        <form method="POST" action="${pageContext.request.contextPath}/releases/${release.id}.html"
                            role="form" class="form-inline" style="display:inline">
                            <input type="submit" class="btn btn-default btn-sm" value="Remove"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(releases)} release(s) | <a href="${pageContext.request.contextPath}/releases/create.html">Create</a></p>
	</div>
</body>
</html>