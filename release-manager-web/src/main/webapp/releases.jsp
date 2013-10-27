<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Releases</title>
    <jsp:include page="/incl/css.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
<body>
        <jsp:include page="/incl/nav.jsp"/>
    <div class="container">
        <div class="page-header">
        <h1>Releases</h1>
        <div class="form-inline pull-right">
            <div class="btn-group">
                <button class="btn btn-primary" data-calendar-nav="prev"><< Prev</button>
                <button class="btn" data-calendar-nav="today">Today</button>
                <button class="btn btn-primary" data-calendar-nav="next">Next >></button>
            </div>
            <div class="btn-group">
                <button class="btn" data-calendar-view="year">Year</button>
                <button class="btn" data-calendar-view="month">Month</button>
                <button class="btn active" data-calendar-view="week">Week</button>
                <button class="btn" data-calendar-view="day">Day</button>
            </div>
        </div>
        <h3></h3>
        </div>
        <div class="clearfix"></div>
        <div id="calendar"></div>
        <table class="table">
            <thead><tr><th>Name</th><th>Status</th><th>When</th><th></th></tr></thead>
            <tbody>
                <c:forEach var="release" items="${releases}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/releases/${release.id}.html">${release.name}</a>
                    </td>
                    <td>
                        ${release.status}
                    </td>
                    <td>
                        ${release.when}
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/releases/${release.id}.html?edit=true">Edit</a>
                        <form method="POST" action="${pageContext.request.contextPath}/releases/${release.id}/delete.html"
                            role="form" class="form-inline" style="display:inline">
                            <input type="submit" class="btn btn-default btn-sm" name="submit" value="Remove"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>
            ${fn:length(releases)} release(s)
                </p>
<p>            <jsp:include page="/incl/watch.jsp"/></p>
                <p>
            <a href="${pageContext.request.contextPath}/releases/create.html" >Create</a></p>
	</div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/underscore-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.min.js"></script>
        <script type="text/javascript">
            var calendar = $('#calendar').calendar({
                events_source: "${pageContext.request.contextPath}/releases_calendar.json",
                view: "week",
                onAfterViewLoad: function(view) {
                        $('.page-header h3').text(this.getTitle());
                        $('.btn-group button').removeClass('active');
                        $('button[data-calendar-view="' + view + '"]').addClass('active');
                },
            });
            $('.btn-group button[data-calendar-nav]').each(function() {
                        var $this = $(this);
                        $this.click(function() {
                                calendar.navigate($this.data('calendar-nav'));
                        });
                });

                $('.btn-group button[data-calendar-view]').each(function() {
                        var $this = $(this);
                        $this.click(function() {
                                calendar.view($this.data('calendar-view'));
                        });
                });

        </script>
</body>
</html>