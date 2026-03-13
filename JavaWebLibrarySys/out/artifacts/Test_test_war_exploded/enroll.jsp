<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/form.css">
</head>
<body>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="index.jsp">退出</a>
        </li>
        <li class="breadcrumb-item">
            <a href="#">简介</a>
        </li>
    </ol>
</nav>
<div class="login-container">
    <h1>请输入用户信息:</h1>
    <form action="/Library/AddUserAction" method="post">
        <div class="form-group">
            <label for="ib">用户名</label>
            <input type="text" id="ib" name="username" required>
        </div>
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" id="name" name="relname" required>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="pwd">确认密码</label>
            <input type="password" id="pwd" name="pwd" required>
        </div>
        <button type="submit" class="submit-btn">注册</button>
    </form>
    <%
        String info = "注册失败";
        if (session.getAttribute("enroll") != null){
            boolean f = (boolean) session.getAttribute("enroll");
            String error_info = (String) session.getAttribute("enroll_info");
            if (f) {
                info = "注册成功";
    %>
    <h3><%=info%></h3>
    <%
    }else {
    %>
    <h3><%=info%>&nbsp;<%=error_info%>
            <%
            }
            session.setAttribute("enroll",null);
        }
    %>
</div>
</body>
</html>
