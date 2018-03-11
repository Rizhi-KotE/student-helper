<%--@elvariable id="group" type="sh.model.Group"--%>
<%--@elvariable id="study" type="sh.model.Study"--%>
<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Study</title>
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
                <a href="<c:url value="/study/list"/>">
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
            <script>
                function sendRecalculate() {
                    var request = new XMLHttpRequest();
                    request.open('GET', '/recalculate?target=3', false);
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
        <strong>Success!</strong> Study was saved
    </div>
</c:if>
<c:if test="${'fail'==message}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> Study was not saved;
    </div>
</c:if>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">
            Name:
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="name" name="name"
                   value="${study.name}">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="hours">
            Hours:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="hours" name="hours"
                   value="${study.hours}">
        </div>
    </div>
    <div class="form-group">

        <label class="control-label col-sm-2" for="professorId">
            Professor:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="professorId" name="professorId">
                <c:forEach items="${professors}" var="professor" varStatus="status">
                    <option
                            <c:if test="${(professor.id==0 && status.first)||professor.id==study.professorId}">selected</c:if>
                            value="${professor.id}">${professor.firstName} ${professor.secondName} ${professor.fatherName}</option>
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
                   value="${study.avgMark}"
                   readonly>
        </div>
    </div>
    <label>
        <input name="oldId" hidden value="${study.id}">
    </label>
    <label>
        <input name="id" hidden value="${study.id}">
    </label>
    <%--@elvariable id="user" type="sh.model.User"--%>
    <c:if test="${user.role== 'ADMIN'}">
        <div class="col-sm-12 form-group">
            <input class=" btn btn-primary" type="submit"
                   formaction="<c:url value="/study/create"/>" formmethod="post">
            <c:if test="${study.id!=0}">
                <button class=" btn btn-warning"
                        formaction="<c:url value="/study/remove"/>">
                    Удалить
                </button>
            </c:if>
        </div>
    </c:if>
</form>
</body>
</html>