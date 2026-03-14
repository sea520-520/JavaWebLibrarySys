package Action;

import dao.BookDao;
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

@WebServlet("/DeleteAction")
public class DeleteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name = req.getParameter("bookname");
        Borrow borrow = new Borrow();
        borrow.setBookname(name);
        ArrayList<Borrow> List = new ArrayList<>();
        Book book = new Book();
        book.setName(name);
        BookDao bdao = new BookDao();
        List = bdao.ifborrowbook(borrow);
        if (List != null && List.size() > 0) {
            HttpSession session = req.getSession();
            session.setAttribute("message", false);
            session.setAttribute("error_del_admin", "剩余书籍未归还，暂不能删除");
            resp.sendRedirect("/Library/deletebook.jsp");
        } else {
            boolean f = bdao.deletebook(book);
            if (f) {
                HttpSession session = req.getSession();
                session.setAttribute("message", true);
                resp.sendRedirect("/Library/deletebook.jsp");
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("message", false);
                session.setAttribute("error_del_admin", "删除失败");
                resp.sendRedirect("/Library/deletebook.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
