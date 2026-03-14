package UserAction;

import dao.BookDao;
import dao.IODao;
import dao.UserDao;
import entity.Book;
import entity.Borrow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BackAction")
public class BackAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String username = (String) req.getSession().getAttribute("username");
        String bookname = req.getParameter("bookname");
        Borrow borrow = new Borrow();
        borrow.setUsername(username);
        borrow.setBookname(bookname);
        UserDao udao = new UserDao();
        boolean b = udao.ifbook(borrow);
        if (b) {
            IODao idao = new IODao();
            boolean f = idao.backbook(borrow);
            if (f) {
                Book temp = new Book();
                temp.setName(bookname);
                BookDao bdao = new BookDao();
                ArrayList<Book> book = new ArrayList<Book>();
                book = bdao.SearchBook(temp);
                if (book != null && book.size() > 0) {
                    Book tmp = new Book();
                    tmp = book.get(0);
                    int sum = tmp.getNum();
                    sum = sum + 1;
                    boolean ff = bdao.addsnum(bookname, sum);
                    if (ff) {
                        HttpSession session = req.getSession();
                        session.setAttribute("back", true);
                        resp.sendRedirect("/Library/backbook.jsp");
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("back", false);
                        resp.sendRedirect("/Library/backbook.jsp");
                    }
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("back", false);
                    resp.sendRedirect("/Library/backbook.jsp");
                }
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("back", false);
                resp.sendRedirect("/Library/backbook.jsp");
            }
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("back", false);
            resp.sendRedirect("/Library/backbook.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
