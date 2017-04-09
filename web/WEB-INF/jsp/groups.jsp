<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Groups</title>
</head>
<body>
<a href="choose-action">Back</a>
<button><<a href="group">Add</a></button>
<table>
    <tr>
        <th>Group number</th>
        <th>Average mark</th>
    </tr>
    <%--@elvariable id="group" type="sh.model.Group"--%>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td><<a href="<c:url value="/group?id=${group.id}"/>"></a>>${group.groupNumber}</a></td>
            <td>${group.avgMark}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>