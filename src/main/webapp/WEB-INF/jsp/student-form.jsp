<%--@elvariable id="group" type="sh.model.Group"--%>
<%--@elvariable id="study" type="sh.model.Study"--%>
<%--@elvariable id="student" type="sh.model.Student"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
</head>
<body class="container">
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
                <a href="<c:url value="/student/list"/>">
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
            <script>
                function sendRecalculate() {
                    var request = new XMLHttpRequest();
                    request.open('GET', '/recalculate?target=1', false);
                    request.send(null);
                    if(request.status==200){
                        location.reload();
                    }
                }

            </script>
            <li role="presentation">
                <a onclick="sendRecalculate()">
                    <span>Recalculate</span>
                    <%--<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>--%>
                </a>
            </li>
        </ul>
    </div>
</nav>


<c:if test="${'success'==message}">
    <div class="alert alert-success">
        <strong>Success!</strong> Student was saved
    </div>
</c:if>
<c:if test="${'fail'==message}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> Student was not saved;
    </div>
</c:if>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="firstName">
            First name:
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="firstName" name="firstName"
                   value="${student.firstName}">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="secondName">
            Second name:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="secondName" name="secondName"
                   value="${student.secondName}">
        </div>
    </div>
    <div class="form-group">

        <label class="control-label col-sm-2" for="groupNumber">
            Group number:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="groupNumber" name="groupNumber">
                <c:forEach items="${groups}" var="group" varStatus="status">
                    <option
                            <c:if test="${(group.groupNumber=='' && status.first)||group.groupNumber==student.groupNumber}">selected</c:if>
                            value="${group.groupNumber}">${group.groupNumber}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="avgMark">
            Average mark:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="avgMark" name="avgMark"
                   value="${student.avgMark}"
                   readonly>
        </div>
    </div>
    <label>
        <input name="oldId" hidden value="${student.id}">
    </label>
    <label>
        <input name="id" hidden value="${student.id}">
    </label>
    <%--@elvariable id="user" type="sh.model.User"--%>
    <c:if test="${user.role== 'ADMIN'}">
        <div class="col-sm-12 form-group">
            <input class=" btn btn-primary" type="submit"
                   formaction="<c:url value="/student/create"/>" formmethod="post">
            <c:if test="${student.id!=0}">
                <button class=" btn btn-warning"
                        formaction="<c:url value="/student/remove"/>">
                    Удалить
                </button>
            </c:if>
        </div>
    </c:if>
</form>
</body>
</html>