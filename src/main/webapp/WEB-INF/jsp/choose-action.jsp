<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Choose action</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
</head>
<c:import url="/WEB-INF/jsp/brand.jsp"/>
<body class="container">
<h1>Choose action</h1>
<div class="send-message">
    <c:if test="${user.role!='ADMIN'}">
        <a class="btn btn-primary" href="<c:url value="/message/send"/>">
            Send message
        </a>
    </c:if>
    <c:if test="${user.role=='ADMIN'}">
        <a class="btn btn-primary" href="<c:url value="/message/list"/>">
            Read message
        </a>
    </c:if>
</div>

<div class="action-list list-group">
    <a class="list-group-item" href="<c:url value="/group/list"/>">Groups</a>
    <a class="list-group-item" href="<c:url value="/student/list"/>">Students</a>
    <a class="list-group-item" href="<c:url value="/professor/list"/>">Professors</a>
    <a class="list-group-item" href="<c:url value="/mark/list"/>">Marks</a>
    <a class="list-group-item" href="<c:url value="/study/list"/>">Studies</a>
    <c:if test="${user.role == 'ADMIN'}">
        <a class="list-group-item" href="<c:url value="/user/list"/>">Users</a>
    </c:if>
</div>

</body>
</html>