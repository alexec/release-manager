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

    <h1>Edit Release</h1>

    <form role="form" method="post" action="${pageContext.request.contextPath}/releases/${release.id}.html">
        <input name="id" type="hidden" value="${release.id}"/>

        <div class="row">
            <div class="form-group col-md-4">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" focus="true" value="${release.name}" id="name"/>
            </div>
            <div class="form-group col-md-3">
                <label for="when">When</label>
                <input type="text" class="form-control" name="when" value="${when}" id="when"/>
                <span class="help-block">e.g "tomorrow"</span>

                <c:if test="${release.executed != null}">
                    <p>Executed at ${release.executed}</p>
                </c:if>
            </div>
            <div class="form-group col-md-1">
                <label for="duration">Duration</label>
                <input type="text" class="form-control" name="duration" value="${duration}" id="duration"/>
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
            <div class="form-group col-md-12">
                <label for="description">Description</label>
                <textarea type="text" class="form-control" name="desc" rows="12"
                          id="description">${release.desc}</textarea>
                <span class="help-block">Markdown formatted</span>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" name="submit" value="Update">Update</button>
        <a href="${pageContext.request.contextPath}/releases/${release.id}.html" class="btn btn-default">View</a>
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
                            <button type="submit" class="btn btn-default btn-sm" name="action" value="REMOVE">Remove
                            </button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
            <!--
        <p>${fn:length(included_components)} component(s)</p>
        -->
            <c:if test="${fn:length(excluded_components) > 0}">
                <form method="post" role="form" class="form-inline"
                      action="${pageContext.request.contextPath}/releases/${release.id}/components.html">
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
                        <input type="text" class="form-control" name="version" id="version" placeholder="Enter version"
                               focus="true">
                    </div>
                    <button type="submit" class="btn btn-default">Add</button>
                </form>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h2>Sign-offs</h2>
            <ul>
                <c:forEach var="i" items="${sign_offs}">
                    <li>${i.key} ${i.value.status}

                        <c:if test="${i.value.status == 'REQUESTED'}">
                            <form method="post" role="form" class="form-inline"
                                  action="${pageContext.request.contextPath}/releases/${release.id}/sign-offs/${i.key}.html">
                                <button type="submit" class="btn btn-primary btn-sm" name="status"
                                        value="AUTHORISED">Authorize
                                </button>
                                <button type="submit" class="btn btn-danger btn-sm" name="status" value="REJECTED">
                                    Reject
                                </button>
                                <button type="submit" class="btn btn-default btn-sm" name="action" value="REMOVE">
                                    Remove
                                </button>
                            </form>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
            <c:if test="${fn:length(excluded_users) > 0}">
                <form method="post" role="form" class="form-inline"
                      action="${pageContext.request.contextPath}/releases/${release.id}/sign-offs.html">
                    <div class="form-group">
                        <label class="sr-only" for="user">Component</label>
                        <select name="user" id="user" class="form-control">
                            <c:forEach var="user" items="${excluded_users}">
                                <option value="${user.name}">${user.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">Add</button>
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>