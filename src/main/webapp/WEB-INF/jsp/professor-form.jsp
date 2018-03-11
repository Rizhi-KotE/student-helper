<%--@elvariable id="group" type="sh.model.Group"--%>
<%--@elvariable id="study" type="sh.model.Study"--%>
<%--@elvariable id="student" type="sh.model.Student"--%>
<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%--@elvariable id="professors" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Professor</title>
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
                <a href="<c:url value="/professor/list"/>">
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
            <script>
                function sendRecalculate() {
                    var request = new XMLHttpRequest();
                    request.open('GET', '/recalculate?target=2', false);
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
        <strong>Success!</strong> Professor was saved
    </div>
</c:if>
<c:if test="${'fail'==message}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> Professor was not saved;
    </div>
</c:if>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="firstName">
            First name:
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="firstName" name="firstName"
                   <c:if test="${professor.id!=0}">readonly</c:if>
                   value="${professor.firstName}">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="secondName">
            Second name:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="secondName" name="secondName"
                   value="${professor.secondName}"
                   <c:if test="${professor.id!=0}">readonly</c:if>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="fatherName">
            Father name:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="fatherName" name="fatherName"
                   value="${professor.fatherName}"
                   <c:if test="${professor.id!=0}">readonly</c:if>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="birthDate">
            Birth date:
        </label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="birthDate" name="birthDate"
                   value="${professor.birthDate}"
                   <c:if test="${professor.id!=0}">readonly</c:if>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="avgMark">
            Average mark
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="avgMark" name="avgMark"
                   value="${professor.avgMark}" readonly>
        </div>
    </div>
    <label>
        <input name="oldId" hidden value="${professor.id}">
    </label>
    <label>
        <input name="id" hidden value="${professor.id}">
    </label>
    <%--@elvariable id="user" type="sh.model.User"--%>
    <c:if test="${user.role== 'ADMIN'}">
        <div class="col-sm-12 form-group">
            <input class=" btn btn-primary" type="submit"
                   formaction="<c:url value="/professor/create"/>" formmethod="post">
            <c:if test="${professor.id!=0}">
                <button class=" btn btn-warning"
                        formaction="<c:url value="/professor/remove"/>">
                    Удалить
                </button>
            </c:if>
        </div>
    </c:if>
</form>
</body>
</html>