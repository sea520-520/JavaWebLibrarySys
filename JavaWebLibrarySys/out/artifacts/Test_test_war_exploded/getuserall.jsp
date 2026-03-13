<%@ page import="entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有用户</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/table.css">
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
<%
    ArrayList<User> List = (ArrayList<User>) session.getAttribute("user");
    String flag = "正常";
%>
<table>
    <thead>
    <tr>
        <th>用户</th>
        <th>用户名</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(List!=null && List.size() > 0)
        {
            for(int i = 0; i < List.size(); i++)
            {
                User b = List.get(i);
    %>
    <tr>
        <td><%=b.getName()%></td>
        <td><%=b.getRelname()%></td>
        <td><%=flag%></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>
