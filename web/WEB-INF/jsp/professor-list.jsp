<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Professors</title>
</head>
<body>
<a href="<c:url value="/choose-action"/>">Back</a>
<button><a href="<c:url value="/professor/read"/>">Add</a></button>
<table>
  <tr>
    <th>First name</th>
    <th>Second name</th>
    <th>Father name</th>
  </tr>
  <%--@elvariable id="professor" type="sh.model.Professor"--%>
  <%--@elvariable id="professors" type="java.util.List<Professor>"--%>
  <c:forEach items="${professors}" var="professor">
    <tr>
      <td><a href="<c:url value="/professor/read?id=${professor.id}"/>">${professor.firstName}</a></td>
      <td>${professor.secondName}</td>
      <td>${professor.fatherName}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>