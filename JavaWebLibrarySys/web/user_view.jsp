<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户界面</title>
    <link type="text/css" rel="stylesheet" href="css/admin.css">
</head>
<body>
<div class="rectangle">
    <table>
        <tr>
            <td>
                <%
                    String name = (String) session.getAttribute("username");
                    session.setAttribute("username",name);
                %>
                <h2 >用户<%=name%>：</h2>
                <%
                %>
            </td>
        </tr>
        <tr>
            <td><button onclick="location.href='user_search.jsp'">搜索书籍</button></td>
            <td><button onclick="location.href='lendbook.jsp'">借阅书籍</button></td>
            <td><button onclick="location.href='backbook.jsp'">归还书籍</button></td>
        </tr>
        <tr>
            <td><button onclick="location.href='change_pwd.jsp'">修改密码</button></td>
            <td><button onclick="location.href='/Library/BorrowAction'">借阅信息</button></td>
            <td><button onclick="location.href='/Library/index.jsp'">退出登录</button></td>
        </tr>
    </table>
</div>
</body>
</html>
