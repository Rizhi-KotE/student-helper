<%--@elvariable id="mark" type="sh.model.Mark"--%>
<%--@elvariable id="study" type="sh.model.Study"--%>
<%--@elvariable id="student" type="sh.model.Student"--%>
<%--@elvariable id="professor" type="sh.model.Professor"--%>
<%--@elvariable id="professors" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mark</title>
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
                <a href="<c:url value="/mark/list"/>">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                </a>
            </li>
            <li role="presentation">
                <a href="<c:url value="/mark/read"/>">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                </a>
            </li>
        </ul>
    </div>
</nav>


<c:if test="${'success'.equals(message)}">
    <div class="alert alert-success">
        <strong>Success!</strong> Mark was saved
    </div>
</c:if>
<c:if test="${'fail'.equals(message)}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> Mark was not saved;
    </div>
</c:if>
<form class="form-horizontal" action="<c:url value="/mark/create"/>" method="get">
    <div class="form-group">
        <label class="control-label col-sm-2" for="studyId">
            Study:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="studyId" name="studyId"
                    <c:if test="${mark.id!=0}">disabled</c:if>>
                ${professor.toString()}
                <%--@elvariable id="studies" type="java.util.List"--%>
                <c:forEach items="${studies}" var="study" varStatus="status">
                <option value="${study.id}"
                        <c:if test="${(mark.id==0 && status.first)||mark.studyId==study.id}">selected</c:if>>
                        ${study.name}
                </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="studentId">
            Student:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="studentId" name="studentId"
                    <c:if test="${mark.id!=0}">disabled</c:if>>
                <%--@elvariable id="students" type="java.util.List"--%>
                <c:forEach items="${students}" var="student" varStatus="status">
                    <option
                            <c:if test="${(mark.id==0 && status.first)||mark.studentId==student.id}">selected</c:if>
                            value="${student.id}">${student.groupNumber} ${student.firstName} ${student.secondName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="date">
            Date:
        </label>
        <div class="col-sm-10">
            <input class="form-control" type="date" id="date" name="date" value="${mark.date}"
                   <c:if test="${mark.id!=0}">disabled</c:if>>
        </div>
    </div>
    <div class="form-group">

        <label class="control-label col-sm-2" for="professorId">
            Professor:
        </label>

        <div class="col-sm-10">
            <select class="form-control" id="professorId" name="professorId"
                    <c:if test="${mark.id!=0}">disabled</c:if>>
                <c:forEach items="${professors}" var="professor" varStatus="status">
                    <option
                            <c:if test="${(mark.id==0 && status.first)||mark.professorId==professor.id}">selected</c:if>
                            value="${professor.id}">${professor.firstName} ${professor.secondName} ${professor.fatherName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="mark">
            Mark:
        </label>
        <div class="col-sm-10">
            <input class="form-control" id="mark" name="mark" value="${mark.mark}"
                   <c:if test="${mark.id!=0}">disabled</c:if>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="comments">
            Comments:
        </label>
        <div class="col-sm-10">
            <textarea class="form-control" id="comments" name="comments" value="${comments.comments}"
                      <c:if test="${mark.id!=0}">disabled</c:if>></textarea>
        </div>
    </div>
    <input name="id" hidden value="${mark.id}">
    <div class="col-sm-12 form-group">
        <c:if test="${mark.id==0}">
            <input class=" btn btn-primary" type="submit">
        </c:if>
    </div>

</form>
</body>
</html>