<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h1>Log in</h1>

<form action="${pageContext.request.contextPath}/login" method="post">
  <label for="email">Email
    <input id="email" name="email" type="text" required>
  </label><br>
  <label for="password">Password
    <input id="password" name="password" type="password" required>
  </label><br>
</form>
</body>
</html>
