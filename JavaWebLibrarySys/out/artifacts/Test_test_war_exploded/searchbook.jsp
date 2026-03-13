<%@ page import="entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索书籍</title>
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
    ArrayList<Book> List = (ArrayList<Book>) session.getAttribute("List");
%>
<table>
    <thead>
    <tr>
        <th>书本编号</th>
        <th>书名</th>
        <th>数量</th>
        <th>位置</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(List!=null && List.size() > 0)
        {
            for(int i = 0; i < List.size(); i++)
            {
                String flag = "不可借";
                Book b = List.get(i);
    %>
    <tr>
        <td><%=b.getIbsn()%></td>
        <td><%=b.getName()%></td>
        <td><%=b.getNum()%></td>
        <td><%=b.getPlace()%></td>
        <%
            if (b.getFlag() == 1){
                flag = "可借";
            }
        %>
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
