<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除书籍</title>
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
    <h1>请输入书名:</h1>
    <form action="/Library/DeleteAction" method="post">
        <div class="form-group">
            <label for="bookname">书名</label>
            <input type="text" id="bookname" name="bookname" required>
        </div>
        <button type="submit" class="submit-btn">删除</button>
    </form>
    <%
        String del;
        if (session.getAttribute("message") != null){
            boolean f = (boolean) session.getAttribute("message");
            if (f) {
                del = "删除成功";
    %>
                <h3><%=del%></h3>
    <%
            }else {
                String del_mes = (String) session.getAttribute("error_del_admin");
    %>
                <h3><%=del_mes%></h3>
    <%
             }
    %>
    <%
            session.setAttribute("message",null);
        }
    %>
</div>
</body>
</html>
