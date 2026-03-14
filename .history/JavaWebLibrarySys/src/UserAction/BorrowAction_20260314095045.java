package UserAction;

import dao.UserDao;
import entity.Borrow;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BorrowAction")
public class BorrowAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        ArrayList<Borrow> book = new ArrayList<Borrow>();
        UserDao udao = new UserDao();
        User user = new User();
        user.setName((String) req.getSession().getAttribute("username"));
        book = udao.getbook(user);
        HttpSession session = req.getSession();
        session.setAttribute("book", book);
        resp.sendRedirect("/Library/user_borrow_list.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
