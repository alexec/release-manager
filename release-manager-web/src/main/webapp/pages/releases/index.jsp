<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Release</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
    <div class="container">
        <ul class="nav nav-pills">
            <li><a href="${pageContext.request.contextPath}/releases/index.html">Releases</a></li>
        </ul>
        <h1>Releases</h1>
        <table class="table">
            <thead><tr><td>ID</td><td>Name</td></tr></thead>
            <tbody>
                <c:forEach var="release" items="${releases}">
                <tr><td>${release.id}</td><td>${release.name}</td></tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(releases)} release(s) | <a href="create.html">Create</a></p>
	</div>
</body>
</html>