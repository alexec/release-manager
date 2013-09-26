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
            <li><a href="${pageContext.request.contextPath}/components/index.html">Components</a></li>
        </ul>
        <h1>New Release</h1>
        <form role="form" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="test" class="form-control" name="name" placeholder="Enter name" focus="true">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</body>
</html>