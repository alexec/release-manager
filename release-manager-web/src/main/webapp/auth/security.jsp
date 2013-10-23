<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
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
                <c:if test="${param.error == 'true'}">
                    <div class="text-warning">
                        Error, please try again.
                    </div>
                </c:if>
                <div class="form-group">
                <label for="j_username">Username</label>
                <input id="j_username" name="j_username" type="text" class="form-control" value="<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %> "/>
            </div>
            <div class="form-group">
                <label for="j_password">Password</label>
                <input id="j_password" name="j_password" type="password" class="form-control" />
            </div>
            <div class="form-group">
                <input type="checkbox" name="_spring_security_remember_me" id="remember_me"/>
                <label for="remember_me">Remember me on this computer</label>
            </div>
            <input type="submit" name="submit" value="Login" class="btn btn-primary"/>
        </form>
    </div>
</body>
</html>