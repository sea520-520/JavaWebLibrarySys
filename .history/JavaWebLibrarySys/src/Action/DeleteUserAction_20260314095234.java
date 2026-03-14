package Action;

import dao.IODao;
import dao.UserDao;
import entity.Borrow;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/DeleteUserAction")
public class DeleteUserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        User user = new User();
        user.setName(username);
        UserDao udao = new UserDao();
        ArrayList<Borrow> List = new ArrayList<>();
        List = udao.getbook(user); // 返回该用户借书所有信息
        // 用户有借书，书未归还不能删除
        if (List != null && List.size() > 0) {
            for (int i = 0; i < List.size(); i++) {
                Borrow b = List.get(i);
                if (b.getFlag() == 0) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user_del", false);
                    session.setAttribute("error_del", "用户有书籍未归还，暂不能删除！");
                    resp.sendRedirect("/Library/del_user.jsp");
                    return;
                }
            }
            boolean f = udao.del_user(username);
            if (f) {
                HttpSession session = req.getSession();
                session.setAttribute("user_del", true);
                resp.sendRedirect("/Library/del_user.jsp");
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user_del", false);
                session.setAttribute("error_del", "用户删除失败");
                resp.sendRedirect("/Library/del_user.jsp");
            }
        } else { // 用户无借书，直接删除
            boolean f = udao.del_user(username);
            if (f) {
                HttpSession session = req.getSession();
                session.setAttribute("user_del", true);
                resp.sendRedirect("/Library/del_user.jsp");
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user_del", false);
                session.setAttribute("error_del", "用户删除失败，请查看用户是否存在");
                resp.sendRedirect("/Library/del_user.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
