<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录</title>
  <link type="text/css" rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login-container">
<h1>登录</h1>
<form action="/Library/LoginAction" method="post">
    <div class="form-group">
      <label for="username">用户名</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div class="form-group">
      <label for="password">密码</label>
      <input type="password" id="password" name="password" required>
    </div>
    <button type="submit" class="submit-btn">登录</button><br />&nbsp;
    <button class="submit-btn" onclick="location.href='enroll.jsp'">注册</button>
</form>
  <%
    if (session.getAttribute("error") != null){
      String f = (String) session.getAttribute("error");
  %>
  <%=f%>
  <%
      session.setAttribute("error",null);
    }
  %>
</div>
</body>
</html>
