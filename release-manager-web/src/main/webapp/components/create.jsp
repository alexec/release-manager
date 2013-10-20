<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Component</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">

        <h1>New Component</h1>
        <form role="form" method="post" action="${pageContext.request.contextPath}/components.html">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" placeholder="Enter name" focus="true"/>
            </div>
            <button type="submit" class="btn btn-default" name="submit">Create</button>
        </form>
    </div>
</body>
</html>