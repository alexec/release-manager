<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
body>
	<h1>Releases</h1>
	<table>
	    <thead><tr><td>ID</td><td>Name</td></tr></thead>
	    <tbody>
	        <c:forEach var="release" items="${releases}">
	        <tr><td>${release.id}</td><td>${release.name}</td></tr>
	        </c:forEach>
	    </tbody>
	</table>
</body>
</html>