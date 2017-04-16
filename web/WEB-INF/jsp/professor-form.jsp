<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Professor</title>
</head>
<body>
<span>${message}</span>
<a href="<c:url value="/professor/list"/>">Back</a>
<a href="<c:url value="/professor/remove?id=${professor.id}"/>"><button>Remove</button></a>
    <form action="<c:url value="/professor/create"/>" method="post">
        <div>
            <label>First name
            <input name="firstName" value="${professor.firstName}">
            </label>
        </div>
        <div>
            <label>Second name
            <input name="secondName" value="${professor.secondName}">
            </label>
        </div>
        <div>
            <label>Father name
            <input name="fatherName" value="${professor.fatherName}">
            </label>
        </div>
        <div>
            <label>Birth date
            <input name="birthDate" value="${professor.birthDate}" type="date">
            </label>
        </div>
        <div>
            <label>Average mark
            <input name="avgMark" value="${professor.avgMark}">
            </label>
        </div>
        <label>
            <input name="id" value="${professor.id}" hidden>
        </label>
        <input type="submit">
    </form>
</body>
</html>