<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Choose action</title>
</head>
<body>
<h1>Choose action</h1>
<div class="send-message">
  <a href="<c:url value="/message/send"/>"><button>Send message</button></a>
  <a href="<c:url value="/message/list"/>"><button>Read message</button></a>
</div>

<ul class="action-list">
  <li><a href="<c:url value="/group/list"/>">Groups</a></li>
  <li><a href="<c:url value="/student/list"/>">Students</a></li>
  <li><a href="<c:url value="/professor/list"/>">Professors</a></li>
  <li><a href="<c:url value="/mark/list"/>">Marks</a></li>
  <li><a href="<c:url value="/study/list"/>">Studies</a></li>
  <li><a href="<c:url value="/user/list"/>">Users</a></li>
</ul>

</body>
</html>