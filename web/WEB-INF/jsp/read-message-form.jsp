<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Read messages</title>
</head>
<body>
<h1>Messages list</h1>
<ul class="messages-list">
  <li>
    <div><span>Принятое сообщение</span></div>
  </li>
</ul>
<a href="<c:url value="/choose-action"/>">Back</a>
</body>
</html>