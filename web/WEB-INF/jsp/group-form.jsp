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
    <title>Group</title>
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
        <div class="navbar-header">
            <a class="navbar-brand" href="#">StudentHelper</a>
        </div>
        <ul class="nav navbar-nav">
            <li role="presentation">
                <a href="<c:url value="/group/list"/>">
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


<c:if test="${'success'==message}">
    <div class="alert alert-success">
        <strong>Success!</strong> Group was saved
    </div>
</c:if>
<c:if test="${'fail'==message}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> Group was not saved;
    </div>
</c:if>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="groupNumber">
            Group number
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="groupNumber" name="groupNumber"
                   <c:if test="${!''.equals(group.groupNumber)}">readonly</c:if>
                   value="${group.groupNumber}">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="avgMark">
            Average mark:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="avgMark" name="avgMark"
                   value="${group.avgMark}" readonly>
        </div>
    </div>
    <label>
        <input name="oldNumber" hidden value="${group.groupNumber}">
    </label>
    <%--@elvariable id="user" type="sh.model.User"--%>
    <c:if test="${user.role== 'ADMIN'}">
        <div class="col-sm-12 form-group">
            <input class=" btn btn-primary" type="submit"
                   formaction="<c:url value="/group/create"/>">
            <c:if test="${!''.equals(group.groupNumber)}">
                <button class=" btn btn-warning"
                        formaction="<c:url value="/group/remove"/>">
                    Delete
                </button>
            </c:if>
        </div>
    </c:if>
</form>
</body>
</html>