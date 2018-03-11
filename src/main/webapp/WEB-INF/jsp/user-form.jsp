<%--@elvariable id="group" type="sh.model.Group"--%>
<%--@elvariable id="user" type="sh.model.User"--%>
<%--@elvariable id="userDto" type="sh.model.User"--%>
<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/src/main/webapp/static/css/bootstrap.min.css"/>">
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
                <a href="<c:url value="/user/list"/>">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                </a>
            </li>
            <c:if test="${user.role== 'ADMIN'}">
                <li role="presentation">
                    <a href="<c:url value="/user/read"/>">
                        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>


<c:if test="${'success'==message}">
    <div class="alert alert-success">
        <strong>Success!</strong> User was saved
    </div>
</c:if>
<c:if test="${'fail'==message}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> User was not saved;
    </div>
</c:if>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="user">
            User:
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="user" name="user"
                   value="${userDto.user}">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">
            Password:
        </label>

        <div class="col-sm-10">
            <input class="form-control" id="password" name="password"
                   value="${userDto.password}">
        </div>
    </div>

    <div class="form-group">

        <label class="control-label col-sm-2" for="role">
            Role:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="role" name="role">
                <option selected value="ADMIN">ADMIN</option>
                <option value="PROFESSOR">PROFESSOR</option>
                <option value="STUDENT">STUDENT</option>
            </select>
        </div>
    </div>
    <label>
        <input name="oldUser" hidden value="${userDto.user}">
    </label>
    <%--@elvariable id="user" type="sh.model.User"--%>
    <c:if test="${user.role== 'ADMIN'}">
        <div class="col-sm-12 form-group">
            <input class=" btn btn-primary" type="submit"
                   formaction="<c:url value="/user/create"/>" formmethod="post">
            <c:if test="${userDto.user !=''}">
                <button class=" btn btn-warning"
                        formaction="<c:url value="/user/remove"/>">
                    Удалить
                </button>
            </c:if>
        </div>
    </c:if>
</form>
</body>
</html>