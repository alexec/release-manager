<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <title>Error</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">
        <h1>Error</h1>
        <p>${pageContext.errorData.throwable}</p>
        </div>
    </body>
</html>