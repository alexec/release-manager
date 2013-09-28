<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Release</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="/incl/nav.jsp"/>

        <h1>Release #${release.id} ${release.name}</h1>
        <table class="table">
            <legent>Components</legend>
            <thead><tr><td>ID</td><td>Name</td></tr></thead>
            <tbody>
                <c:forEach var="component" items="${components}">
                <tr><td>${component.id}</td><td>${component.name}</td></tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(components)} component(s)</div>
</body>
</html>