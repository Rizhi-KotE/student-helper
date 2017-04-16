<%--@elvariable id="group" type="sh.model.Group"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Groups</title>
</head>
<body>
<a href="<c:url value="/group/list"/>">Back</a>
    <form action="<c:url value="/group/create"/>" method="post">
        <div>
            <label>
                Group number
                <input name="groupNumber" value="${group.groupNumber}">
            </label>
        </div>
        <div>
            <label>Average mark
                <input name="avgMark" value="${group.avgMark}">
            </label>
        </div>
        <label>
            <input name="oldNumber" value="${group.groupNumber}" hidden>
        </label>
        <input type="submit">
    </form>
</body>
</html>