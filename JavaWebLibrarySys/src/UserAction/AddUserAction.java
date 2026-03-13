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

@WebServlet("/AddUserAction")
public class AddUserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name = req.getParameter("username");
        String relname = req.getParameter("relname");
        String password = req.getParameter("password");
        String pwd = req.getParameter("pwd");
        if (password.equals(pwd)){
            User u = new User();
            u.setName(name);
            u.setRelname(relname);
            u.setPassword(password);
            UserDao udao = new UserDao();
            String f = udao.login(u);
            if (!f.equals("user")){
                boolean b = udao.adduser(u);
                if (b){
                    HttpSession session = req.getSession();
                    session.setAttribute("username",u.getName());
                    resp.sendRedirect("/Library/user_view.jsp");
                }else {
                    HttpSession session = req.getSession();
                    session.setAttribute("enroll",false);
                    session.setAttribute("enroll_info","请检查输入信息是否正确");
                    resp.sendRedirect("/Library/enroll.jsp");
                }
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("enroll",false);
                session.setAttribute("enroll_info","用户已存在");
                resp.sendRedirect("/Library/enroll.jsp");
            }
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("enroll",false);
            session.setAttribute("enroll_info","密码错误");
            resp.sendRedirect("/Library/enroll.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
