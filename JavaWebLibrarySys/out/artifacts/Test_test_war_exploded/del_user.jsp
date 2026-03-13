<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除用户</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/form.css">
</head>
<body>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="admin_view.jsp">首页</a>
        </li>
        <li class="breadcrumb-item">
            <a href="#">简介</a>
        </li>
    </ol>
</nav>
<div class="login-container">
    <h1>请输入用户:</h1>
    <form action="/Library/DeleteUserAction" method="post">
        <div class="form-group">
            <label for="username">用户</label>
            <input type="text" id="username" name="username" required>
        </div>
        <button type="submit" class="submit-btn">删除</button>
    </form>
    <%
        String del ;
        if (session.getAttribute("user_del") != null){
            boolean f = (boolean) session.getAttribute("user_del");
            if (f) {
                del = "用户删除成功";
    %>
            <h3><%=del%></h3>
    <%
            }else {
                String error_del = (String) session.getAttribute("error_del");
    %>
                <h3><%=error_del%></h3>
    <%
            }
    %>
    <%
            session.setAttribute("user_del",null);
        }
    %>
</div>
</body>
</html>
