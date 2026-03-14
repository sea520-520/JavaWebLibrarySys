package Action;

import JDBC.GetCorn;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
    UserDao udao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求编码、响应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String name = request.getParameter("username");
        String pwd = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setPassword(pwd);
        String result = udao.login(user);
        if (result.equals("user")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            response.sendRedirect("/Library/user_view.jsp");
        } else if (result.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", name);
            response.sendRedirect("/Library/admin_view.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "信息错误");
            response.sendRedirect("/Library/index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
