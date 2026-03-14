package UserAction;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PwdAction")
public class PwdAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String username = (String) req.getSession().getAttribute("username");
        String pwd = req.getParameter("pwd");
        String n_pwd = req.getParameter("n_pwd");
        String a_n_pwd = req.getParameter("a_n_pwd");
        User user = new User();
        user.setName(username);
        user.setPassword(pwd);
        UserDao udao = new UserDao();
        String result = udao.login(user);
        if (result.equals("error")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("flag", false);
            session.setAttribute("mes_error", "原密码错误");
            resp.sendRedirect("/Library/change_pwd.jsp");
        } else {
            if (!n_pwd.equals(a_n_pwd)) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("flag", false);
                session.setAttribute("mes_error", "密码错误");
                resp.sendRedirect("/Library/change_pwd.jsp");
            } else {
                boolean f = udao.changepwd(user, n_pwd);
                if (f) {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("flag", true);
                    resp.sendRedirect("/Library/change_pwd.jsp");
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("flag", false);
                    session.setAttribute("mes_error", "修改失败");
                    resp.sendRedirect("/Library/change_pwd.jsp");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
