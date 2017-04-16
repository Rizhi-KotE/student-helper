<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Professors</title>
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
                        <a href="<c:url value="/professor/read"/>">
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
                <th>Father name</th>
                <th>Birth date</th>
                <th>Average mark</th>
            </tr>
            <%--@elvariable id="professor" type="sh.model.Professor"--%>
            <tbody>
            <c:forEach items="${professors}" var="professor">
                <tr>
                    <td>
                        <a href="<c:url value="/professor/read?id=${professor.id}"/>">go to
                        </a>
                    </td>
                    <td>${professor.firstName}</td>
                    <td>${professor.secondName}</td>
                    <td>${professor.fatherName}</td>
                    <td>${professor.birthDate}</td>
                    <td>${professor.avgMark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
</body>
</html>