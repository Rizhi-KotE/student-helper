<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="student" type="sh.model.Student"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
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
                        <a href="<c:url value="/student/read"/>">
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
                <th>First name</th>
                <th>Second name</th>
                <th>Group number</th>
                <th>Average mark</th>
            </tr>
            <%--@elvariable id="student" type="sh.model.Professor"--%>
            <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>
                        <a href="<c:url value="/student/read?id=${student.id}"/>">go to
                        </a>
                    </td>
                    <td>${student.firstName}</td>
                    <td>${student.secondName}</td>
                    <td>${student.groupNumber}</td>
                    <td>${student.avgMark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
</body>
</html>