<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="study" type="sh.model.Study"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Studies</title>
</head>
<link rel="stylesheet" type="text/css" href="<c:url value="/src/main/webapp/static/css/bootstrap.min.css"/>">
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
                        <a href="<c:url value="/study/read"/>">
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
                <th>Name</th>
                <th>Hours</th>
                <th>Professor id</th>
                <th>Average mark</th>
            </tr>
            <%--@elvariable id="study" type="sh.model.Professor"--%>
            <tbody>
            <c:forEach items="${studies}" var="study">
                <tr>
                    <td>
                        <a href="<c:url value="/study/read?id=${study.id}"/>">go to
                        </a>
                    </td>
                    <td>${study.name}</td>
                    <td>${study.hours}</td>
                    <td>${study.professorId}</td>
                    <td>${study.avgMark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>