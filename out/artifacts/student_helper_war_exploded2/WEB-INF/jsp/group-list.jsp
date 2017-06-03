<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Groups</title>
</head>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <style>
                .glyphicon {
                    font-size: 1.5em;
                }
            </style>
            <div class="navbar-header">
                <a class="navbar-brand" href="#">StudentHelper</a>
            </div>
            <ul class="nav navbar-nav">
                <li role="presentation">
                    <a href="<c:url value="/choose-action"/>">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    </a>
                </li>
                <c:if test="${user.role== 'ADMIN'}">
                    <li role="presentation">
                        <a href="<c:url value="/group/read"/>">
                            <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
    <div class="container">
        <table class="table">
            <tr>
                <th></th>
                <th>Group number</th>
                <th>Average group</th>
            </tr>
            <%--@elvariable id="group" type="sh.model.Group"--%>
            <tbody>
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td>
                        <a href="<c:url value="/group/read?groupNumber=${group.groupNumber}"/>">go to
                        </a>
                    </td>
                    <td>${group.groupNumber}</td>
                    <td>${group.avgMark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
</body>
</html>