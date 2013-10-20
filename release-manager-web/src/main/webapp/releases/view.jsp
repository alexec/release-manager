<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Release</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">

        <h1>${release.name}</h1>
        <div class="row">
        <div class="col-md-7">
        <p>${release.status}</p>
        </div>
        <div class="col-md-3">
                 <p>${when}</p>
         </div>
        <div class="col-md-1">
                 <p>${duration}</p>
         </div>
         </div>
         <div>${desc}</div>
         <div class="row">
         <div class="col-md-6">
            <h2>Components</h2>
        <ul>
                <c:forEach var="component" items="${included_components}">
                    <li>${component.component.name} ${component.version}</li>
                </c:forEach>
        </ul>
        </div>
        <div class="col-md-6">
            <h2>Sign-offs</h2>
        <ul>
                <c:forEach var="i" items="${sign_offs}">
                    <li>${i.key.email} ${i.value.status}</li>
                </c:forEach>
        </ul>
        </div>
        </div>
        <p>
         <a href="${pageContext.request.contextPath}/releases/${release.id}.html?edit=true">Edit</a>
</p>
    </body>
</html>