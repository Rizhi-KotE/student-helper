<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="student" type="sh.model.Student"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student form</title>
</head>
<body>
<span>${message}</span>
<form class="student-form" action="<c:url value="/student/create"/>" method="post">
    <div>
        <label for="firstName">First name
            <input id="firstName" name="firstName" type="text" value="${student.firstName}">
        </label>
    </div>
    <div>
        <label for="secondName">Second name
            <input id="secondName" name="secondName" type="text" value="${student.secondName}">
        </label>
    </div>
    <div>
        <label for="avgMark">Average mark
            <input id="avgMark" name="avgMark" type="text" value="${student.avgMark}"></label>
    </div>
    <label>
        <input name="id" value="${student.id}" hidden>
    </label>
    <div>
        <label>Group number
        <input type="text" name="groupNumber" value="${student.groupNumber}">
        </label>
    </div>
    <div>
        <button type="submit">${action}</button>
    </div>
</form>

<div class="student-form controls">
    <a href="<c:url value="/student/list"/>">Back</a>
    <button><a href="<c:url value="/student/remove?id=${student.id}"/>"></a></button>
    <%--<form action="student" method="delete">--%>
        <%--<button>Delete</button>--%>
    <%--</form>--%>
</div>
</body>
</html>