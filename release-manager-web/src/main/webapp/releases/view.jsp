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

        <h1>${release.name}</h1>
        <p>${release.status}</p>
        <p>${release.when}</p>
         <div>${desc}</div>
            <h2>Components</h2>
        <ul class="list-inline">
                <c:forEach var="component" items="${included_components}">
                    <li>${component.name}</li>
                </c:forEach>
        </ul>
            <h2>Sign-offs</h2>
        <ul class="list-inline">
                <c:forEach var="i" items="${sign_offs}">
                    <li>${i.key.email} ${i.value.status}</li>
                </c:forEach>
        </ul>
        <p class="help-block">${created}
         <a href="${pageContext.request.contextPath}/releases/${release.id}.html?edit=true">Edit</a>
</p>
    </body>
</html>