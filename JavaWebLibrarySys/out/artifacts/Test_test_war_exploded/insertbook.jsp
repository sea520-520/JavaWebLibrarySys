<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>插入书籍</title>
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
    <h1>请输入书籍信息:</h1>
    <form action="/Library/AddAction" method="post">
        <div class="form-group">
            <label for="ibsn">书号</label>
            <input type="text" id="ibsn" name="ibsn" required>
        </div>
        <div class="form-group">
            <label for="bookname">书名</label>
            <input type="text" id="bookname" name="bookname" required>
        </div>
        <div class="form-group">
            <label for="num">数量</label>
            <input type="text" id="num" name="num" required>
        </div>
        <div class="form-group">
            <label for="place">地点</label>
            <input type="text" id="place" name="place" required>
        </div>
        <div class="form-group">
            <label for="flag">状态( 可借 || 不可借 )</label>
            <input type="text" id="flag" name="flag" required>
        </div>
        <button type="submit" class="submit-btn">添加</button>
    </form>
    <%
        String info = "插入失败";
        if (session.getAttribute("insert") != null){
            boolean f = (boolean) session.getAttribute("insert");
            String error_info = (String) session.getAttribute("error_info");
            if (f) {
                info = "插入成功";
    %>
                <h3><%=info%></h3>
    <%
            }else {
    %>
                <h3><%=info%>&nbsp;<%=error_info%>
    <%
            }
            session.setAttribute("insert",null);
        }
    %>
</div>
</body>
</html>
