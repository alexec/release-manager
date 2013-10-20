<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value="/"/>">Release Manager</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">

    <li><a href="<c:url value="/releases.html"/>">Releases</a></li>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li><a href="<c:url value="/components.html"/>">Components</a></li>
    </sec:authorize>
    <li><a href="<c:url value="/watches.html"/>">Watches</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/auth/logout.html"/>">Logout ${pageContext.request.userPrincipal.principal.username}</a></li>

          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
</ul>
