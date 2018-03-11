<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Marks</title>
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
            <c:import url="/WEB-INF/jsp/brand.jsp"/>
            <ul class="nav navbar-nav">
                <li role="presentation">
                    <a href="<c:url value="/choose-action"/>">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    </a>
                </li>
                <c:if test="${user.role == 'ADMIN' || user.role=='PROFESSOR'}">
                    <li role="presentation">
                        <a href="<c:url value="/mark/read"/>">
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
                <th>Study id</th>
                <th>Student id</th>
                <th>Date</th>
                <th>Professor id</th>
                <th>Mark</th>
                <th>Comments</th>
            </tr>
            <%--@elvariable id="mark" type="sh.model.Mark"--%>
            <tbody style="overflow: scroll">
            <c:forEach items="${marks}" var="mark">
                <tr>
                    <td>
                        <a href="<c:url value="/mark/read?id=${mark.id}"/>">go to
                        </a>
                    </td>
                    <td>${mark.studyId}</td>
                    <td>${mark.studentId}</td>
                    <td>${mark.date}</td>
                    <td>${mark.professorId}</td>
                    <td>${mark.mark}</td>
                    <td>${mark.comments}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
</body>
</html>