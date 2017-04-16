<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Send message</title>
</head>
<body>
<h1>Send form</h1>
<form class="send-message-form">
  <div>
    <label style="display: block">Text
    <textarea style="display: block"></textarea>
    </label>
  </div>
  <div>
    <a href="<c:url value="/choose-action"/>">Back</a>
    <input type="submit">
  </div>
</form>

</body>
</html>