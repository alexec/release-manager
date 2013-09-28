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
                <c:forEach var="component" items="${included_components}">
                <tr><td>${component.id}</td><td>${component.name}</td></tr>
                </c:forEach>
            </tbody>
        </table>
        <p>${fn:length(included_components)} component(s)</p>
        <form method="post" role="form" class="form-inline">
            <div class="form-group">
                <label class="sr-only" for="component_id">Component</label>
                <select name="component_id" id="component_id" class="form-control">
                    <c:forEach var="component" items="${excluded_components}">
                    <option value="${component.id}">${component.name}</option>
                    </c:forEach>
                </select>
            </div>
            <!--
            <div class="form-group">
                <label class="sr-only" for="version">Version</label>
                <input type="text" class="form-control" name="version" id="version" placeholder="Enter version" focus="true">
            </div>
            -->
            <button type="submit" class="btn btn-default">Add</button>
        </form>
</body>
</html>