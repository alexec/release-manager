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

        <h1>Edit Release</h1>
        <form role="form" method="post" action="${pageContext.request.contextPath}/releases/${release.id}.html">
            <input name="id" type="hidden" value="${release.id}"/>

            <div class="row">
            <div class="form-group col-md-4">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" focus="true" value="${release.name}"/>
            </div>
            <div class="form-group col-md-2">
                <label for="when">When</label>
                <input type="text" class="form-control" name="when" value="${release.when}"/>
                <span class="help-block">e.g "tomorrow"</span>
            </div>
            <div class="form-group col-md-2">
                <label for="when">Duration</label>
                <input type="text" class="form-control" name="duration" value="${release.duration}"/>
                <span class="help-block">e.g  "2 hours", or "2h 3m</span>
            </div>
            <div class="form-group col-md-4">
                <label for="status">Status</label>
                <select name="status" id="status" class="form-control">
                    <option ${release.status == 'REQUESTED' ? 'selected' : ''}>REQUESTED</option>
                    <option ${release.status == 'EXECUTED' ? 'selected' : ''}>EXECUTED</option>
                    <option ${release.status == 'VERIFIED' ? 'selected' : ''}>VERIFIED</option>
                </select>
            </div>
            </div>
            <div class="row">
            <div class="col-md-12">
            <div class="form-group">
                <label for="description">Description</label>
                <textarea type="text" class="form-control" name="desc">${release.desc}</textarea>
                <span class="help-block">Markdown formatted</span>
            </div>
            <button type="submit" class="btn btn-default" name="submit" value="Update">Update</button>
            </div>
            </div>
        </form>
         <div class="row">
         <div class="col-md-6">
            <h2>Components</h2>
        <ul>
                <c:forEach var="component" items="${included_components}">
                    <li>
                    ${component.component.name}:${component.version}
                     <form method="post" role="form" class="form-inline"
                            action="${pageContext.request.contextPath}/releases/${release.id}/components/${component.component.id}.html">
                            <button type="submit" class="btn btn-default btn-sm" name="action" value="REMOVE">Remove</button>
                        </form>
                    </li>
                </c:forEach>
        </ul>
        <!--
        <p>${fn:length(included_components)} component(s)</p>
        -->
         <c:if test="${fn:length(excluded_components) > 0}">
        <form method="post" role="form" class="form-inline" action="${pageContext.request.contextPath}/releases/${release.id}/components.html">
            <div class="form-group">
                <label class="sr-only" for="component_id">Component</label>
                <select name="component_id" id="component_id" class="form-control">
                    <c:forEach var="component" items="${excluded_components}">
                    <option value="${component.id}">${component.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label class="sr-only" for="version">Version</label>
                <input type="text" class="form-control" name="version" id="version" placeholder="Enter version" focus="true">
            </div>
            <button type="submit" class="btn btn-default">Add</button>
        </form>
        </c:if>
        </div>

         <div class="row">
         <div class="col-md-6">
            <h2>Sign-offs</h2>
        <ul>
                <c:forEach var="i" items="${sign_offs}">
                    <li>${i.key.email} ${i.value.status}

                        <c:if test="${i.value.status == 'REQUESTED'}">
                        <form method="post" role="form" class="form-inline"
                            action="${pageContext.request.contextPath}/releases/${release.id}/sign-offs/${i.key.id}.html">
                            <button type="submit" class="btn btn-primary btn-sm" name="status" value="AUTHORISED">Authorize</button>
                            <button type="submit" class="btn btn-danger btn-sm" name="status" value="REJECTED">Reject</button>
                            <button type="submit" class="btn btn-default btn-sm" name="action" value="REMOVE">Remove</button>
                        </form>
                        </c:if>
                    </li>
                </c:forEach>
        </ul>
        <c:if test="${fn:length(excluded_users) > 0}">
        <form method="post" role="form" class="form-inline" action="${pageContext.request.contextPath}/releases/${release.id}/sign-offs.html">
            <div class="form-group">
                <label class="sr-only" for="user_id">Component</label>
                <select name="user_id" id="user_id" class="form-control">
                    <c:forEach var="i" items="${excluded_users}">
                    <option value="${i.id}">${i.email}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">Add</button>
        </form>
        </c:if>
        </div>
        </div>

    </body>
</html>