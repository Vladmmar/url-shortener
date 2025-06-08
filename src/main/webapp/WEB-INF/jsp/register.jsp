<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>

<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/register" method="post">
    <label for="first-name">First name
        <input id="first-name" name="first-name" type="text" required>
    </label><br>
    <label for="last-name">Last name
        <input id="last-name" name="last-name" type="text" required>
    </label><br>
    <label for="email">Email
        <input id="email" name="email" type="text" required>
    </label><br>
    <label for="password">Password
        <input id="password" name="password" type="password" required>
    </label><br>
    <label for="password">Confirm the password
        <input id="confirm-password" name="confirm-password" type="password" required>
    </label><br>
    <label for="profile-image">Profile image
        <input id="profile-image" name="profile-image" type="file">
    </label><br>
    <label for="birthday">birthday
        <input id="birthday" name="birthday" type="date" required>
    </label><br>
    <button type="submit">Submit</button>
</form>

<a href="${pageContext.request.contextPath}/login">login</a>
</body>
</html>
