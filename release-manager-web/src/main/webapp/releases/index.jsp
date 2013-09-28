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
            <thead><tr><td>ID</td><td>Name</td></tr></thead>
            <tbody>
                <c:forEach var="release" items="${releases}">
                <tr>
                    <td>
                        <a href="${release.id}.html">${release.id}</a>
                    </td>
                    <td>
                        <a href="${release.id}.html">${release.name}</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(releases)} release(s) | <a href="create.html">Create</a></p>
	</div>
</body>
</html>