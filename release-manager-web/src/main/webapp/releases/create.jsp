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
        <div class="row">
        <form role="form" method="post" action="${pageContext.request.contextPath}/releases.html">
            <div class="form-group col-md-6">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" placeholder="Enter name" focus="true"/>
            </div>
            <div class="form-group col-md-3">
                <label for="when">When</label>
                <input type="text" class="form-control" name="when" placeholder="Enter when"/>
                <span class="help-block">e.g "tomorrow"</span>
            </div>
            <div class="form-group col-md-3">
                <label for="when">Duration</label>
                <input type="text" class="form-control" name="duration" placeholder="Enter duration"/>
                <span class="help-block">e.g  "2 hours", or "2h 3m</span>
            </div>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea type="text" class="form-control" name="desc" placeholder="Enter description"></textarea>
                <span class="help-block">Markdown formatted</span>
            </div>
            <button type="submit" class="btn btn-default">Create</button>
        </form>
    </div>
</body>
</html>