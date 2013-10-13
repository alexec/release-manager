<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Release</title>
    <jsp:include page="/incl/css.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="/incl/nav.jsp"/>
        <h1>New Release</h1>
        <form role="form" method="post" action="${pageContext.request.contextPath}/releases.html">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" placeholder="Enter name" focus="true">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea type="text" class="form-control" name="desc" placeholder="Enter description"></textarea>
            </div>
            <button type="submit" class="btn btn-default">Create</button>
        </form>
    </div>
</body>
</html>