<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
  <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
  <link type="text/css" rel="stylesheet" href="css/form.css">
</head>
<body>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="user_view.jsp">首页</a>
    </li>
    <li class="breadcrumb-item">
      <a href="#">简介</a>
    </li>
  </ol>
</nav>
<div class="login-container">
  <h1>请输入用户信息:</h1>
  <form action="/Library/PwdAction" method="post">
    <div class="form-group">
      <label for="pwd">原密码</label>
      <input type="password" id="pwd" name="pwd" required>
    </div>
    <div class="form-group">
      <label for="n_pwd">新密码</label>
      <input type="password" id="n_pwd" name="n_pwd" required>
    </div>
    <div class="form-group">
      <label for="a_n_pwd">确认密码</label>
      <input type="password" id="a_n_pwd" name="a_n_pwd" required>
    </div>
    <button type="submit" class="submit-btn">修改</button>
  </form>
  <%
    String change_pwd = "修改失败";
    if (session.getAttribute("flag") != null){
      boolean f = (boolean) session.getAttribute("flag");
      if (f) {
        change_pwd = "修改成功";
  %>
        <h3><%=change_pwd%></h3>
  <%
      }else {
        String error_pwd = (String) session.getAttribute("mes_error");
  %>
        <h3><%=error_pwd%>
      <%
      }
      session.setAttribute("flag",null);
    }
    %>
</div>
</body>
</html>
