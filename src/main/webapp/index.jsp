<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Student helper</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
</head>
<body>
<h1>Welcome</h1>
<div class="container">
    <div class="row">
      <form class="col-sm-6 form" action="login" method="post">
        <div class="form-group">
          <input placeholder="Username" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <input placeholder="Password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="form-control submit">Login</button>
      </form>
      <form class="col-sm-6 form" action="registrate" method="post">
        <div class="form-group">
          <input placeholder="Username" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
          <input placeholder="Password" class="form-control" id="password" name="password">
        </div>
        <div class="form-group">
          <input placeholder="Repeat password" class="form-control" id="repeat" name="repeat">
        </div>
        <button type="submit" class="form-control submit">Registrate</button>
      </form>
    </div>
</body>
</html>