<%@ page import="entity.Borrow" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: 云轩
  Date: 2024/6/12
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户借阅信息</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/table.css">
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
<%
    ArrayList<Borrow> List = (ArrayList<Borrow>) session.getAttribute("book");
%>
<table>
    <thead>
    <tr>
        <th>书名</th>
        <th>借阅时间</th>
        <th>借阅状态</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(List!=null && List.size() > 0)
        {
            for(int i = 0; i < List.size(); i++)
            {
                Borrow b = List.get(i);
    %>
    <tr>
        <td><%=b.getBookname()%></td>
        <td><%=b.getDataime()%></td>
        <%
            String flag_book = "已归还";
            if (b.getFlag()==0){
                flag_book = "未归还";
            }
            %>
        <td><%=flag_book%></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>
