<%@ page import="sh.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="static java.lang.String.format" %>
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
<a href="student">
    <button>Add</button>
</a>


<ul class="students-list">

    <c:forEach var="student" items="${students}">
        <li><a href="<c:url value="student?id=${student.id}"/>" >${student.firstName}</a></li>
    </c:forEach>
</ul>
<a href="choose-action">Back</a>
</body>
</html>