<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索书籍</title>
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
    <form action="/Library/SearchAction" method="post">
        <div class="form-group">
            <label for="bookname">书名</label>
            <input type="text" id="bookname" name="bookname" required>
        </div>
        <button type="submit" class="submit-btn">搜索</button>
    </form>
    <%
        String mes = "搜索失败";
        if (session.getAttribute("search") != null){
            boolean f = (boolean) session.getAttribute("search");
            if (f) {
                mes = "搜索成功";
    %>          <h3><%=mes%></h3>
    <%
            }else {
    %>          <h3><%=mes%></h3>
    <%
            }
            session.setAttribute("search",null);
        }
    %>
</div>
</body>
</html>
