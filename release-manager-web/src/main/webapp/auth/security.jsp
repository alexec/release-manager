<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Login</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
    <div class="container">

	        <h1>Login</h1>
	        <form action="<c:url value="/auth/login.html"/>" method="post" role="form">
                <c:if test="${not empty error}">
                <div class="error">
                    Your login attempt was not successful, try again.
                    <br />
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
            <div class="form-group">
                <label for="j_username">Username</label>
                <input id="j_username" name="j_username" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="j_password">Password</label>
                <input id="j_password" name="j_password" type="password" class="form-control" />
            </div>
            <input type="submit" name="submit" value="Login" class="btn btn-primary"/>
        </form>
    </div>
</body>
</html>