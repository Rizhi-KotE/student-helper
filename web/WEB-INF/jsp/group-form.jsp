<%--@elvariable id="group" type="sh.model.Group"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Groups</title>
</head>
<body>
<a href="groups">Back</a>
<table>
    <tr>
        <th>Group number</th>
        <th>Average mark</th>
    </tr>
    <form action="group" method="post">
        <div>
            <label>Group number</label>
            <input name="groupNumber" value="${group.groupNumber}">
        </div>
        <div>
            <label>Average mark</label>
            <input name="avgMark" value="${group.avgMark}">
        </div>
        <input name="id" value="${group.id}" hidden>
        <input type="submit">
    </form>
</table>
</body>
</html>