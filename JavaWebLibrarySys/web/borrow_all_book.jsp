<%@ page import="entity.Borrow" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅信息</title>
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
    ArrayList<Borrow> List = (ArrayList<Borrow>) session.getAttribute("borrow");
%>
<table>
    <thead>
    <tr>
        <th>用户</th>
        <th>用户名</th>
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
        <td><%=b.getUsername()%></td>
        <td><%=b.getRelname()%></td>
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
