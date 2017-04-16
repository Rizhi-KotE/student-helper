<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
</head>
<body>
<h1>Students list</h1>

<a href="<c:url value='student/read'/>">
    <button>Add</button>
</a>


<ul class="students-list">

    <c:forEach var="student" items="${students}">
        <a href="<c:url value="/student/read?id=${student.id}"/>">${student.id}</a>
    </c:forEach>
</ul>
<a href="<c:url value="/choose-action"/>">Back</a>
</body>
</html>