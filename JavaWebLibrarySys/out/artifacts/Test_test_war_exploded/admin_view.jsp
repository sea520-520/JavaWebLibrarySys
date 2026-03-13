<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>管理员界面</title>
    <link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>
<div class="rectangle">
    <table>
        <tr>
            <td><button onclick="location.href='/Library/BookAction'">查看书籍</button></td>
            <td><button onclick="location.href='changebook.jsp'">修改书籍</button></td>
            <td><button onclick="location.href='deletebook.jsp'">删除书籍</button></td>
            <td><button onclick="location.href='insertbook.jsp'">插入书籍</button></td>
        </tr>
        <tr>
            <td><button onclick="location.href='/Library/UserAction'">查看用户</button></td>
            <td><button onclick="location.href='/Library/BorrowAllAction'">借阅记录</button></td>
            <td><button onclick="location.href='del_user.jsp'">删除用户</button></td>
            <td><button onclick="location.href='/Library/index.jsp'">退出登录</button></td>
        </tr>
    </table>
</div>
</body>
</html>
