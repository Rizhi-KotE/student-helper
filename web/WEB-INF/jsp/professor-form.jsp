<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Professor</title>
</head>
<body>
<a href="professors">Back</a>
    <form action="professor" method="post">
        <div>
            <label>First name</label>
            <input name="firstName" value="${professor.firstName}">
        </div>
        <div>
            <label>Secong name</label>
            <input name="secondName" value="${professor.secondName}">
        </div>
        <div>
            <label>Father name</label>
            <input name="fatherName" value="${professor.fatherName}">
        </div>
        <div>
            <label>Birth date</label>
            <input name="birthDate" value="${professor.birthDate}" type="date">
        </div>
        <div>
            <label>Average mark</label>
            <input name="avgMark" value="${professor.avgMark}">
        </div>
        <input name="id" value="${professor.id}" hidden>
        <input type="submit">
    </form>
</body>
</html>