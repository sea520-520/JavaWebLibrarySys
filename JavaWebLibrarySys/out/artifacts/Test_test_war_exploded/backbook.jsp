<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>归还书籍</title>
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
    <h1>请输入书名:</h1>
    <form action="/Library/BackAction" method="post">
        <div class="form-group">
            <label for="bookname">书名</label>
            <input type="text" id="bookname" name="bookname" required>
        </div>
        <button type="submit" class="submit-btn">归还</button>
    </form>
    <%
        String mes = "归还失败";
        if (session.getAttribute("back") != null){
            boolean f = (boolean) session.getAttribute("back");
            if (f) {
                mes = "归还成功";
    %>          <h3><%=mes%></h3>
    <%
    }else {
    %>          <h3><%=mes%></h3>
    <%
            }
            session.setAttribute("back",null);
        }
    %>
</div>
</body>
</html>
